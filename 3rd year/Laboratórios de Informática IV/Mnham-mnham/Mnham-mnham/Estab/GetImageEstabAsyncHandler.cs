using System;
using System.Web;
using System.Threading;
using System.Collections.Generic;
using Mnham_mnham.Estab;
using Mnham_mnham.Utils;
using System.Drawing.Imaging;

namespace Mnham_mnham.Estab
{
    class GetImageEstabAsyncHandler : IHttpAsyncHandler
    {
        public bool IsReusable { get { return false; } }

        public GetImageEstabAsyncHandler()
        {
        }
        public IAsyncResult BeginProcessRequest(HttpContext context, AsyncCallback cb, Object extraData)
        {
            AsynchOperation asynch = new AsynchOperation(cb, context, extraData);
            asynch.StartAsyncWork();
            return asynch;
        }

        public void EndProcessRequest(IAsyncResult result)
        {
        }

        public void ProcessRequest(HttpContext context)
        {
            throw new InvalidOperationException();
        }
    }

    class AsynchOperation : IAsyncResult
    {
        private bool _completed;
        private Object _state;
        private AsyncCallback _callback;
        private HttpContext _context;

        bool IAsyncResult.IsCompleted { get { return _completed; } }
        WaitHandle IAsyncResult.AsyncWaitHandle { get { return null; } }
        Object IAsyncResult.AsyncState { get { return _state; } }
        bool IAsyncResult.CompletedSynchronously { get { return false; } }

        public AsynchOperation(AsyncCallback callback, HttpContext context, Object state)
        {
            _callback = callback;
            _context = context;
            _state = state;
            _completed = false;
        }

        public void StartAsyncWork()
        {
            ThreadPool.QueueUserWorkItem(new WaitCallback(StartAsyncTask), null);
        }

        private void StartAsyncTask(Object workItemState)
        {
            List<ItemOnMenu> l = ((List<ItemOnMenu>)_context.Session["Items"]);
            int index = int.Parse(_context.Request.QueryString["id"]);
            if (l != null && l.Count > index)
            {
                ItemOnMenu s = l[index];

                _context.Response.ContentType = "image/" + ReturnsImageFormat.ReturnImageFormatString(s.format);
                if (s.image != null)
                {
                    _context.Response.Write(StreamConversion.StreamToByteArray(s.image));
                }

                _completed = true;
                _callback(this);
            }
            else
            {
                _context.Response.StatusCode = 200;
                _context.Response.Status = "Bad Request";
            }
        }
    }
}