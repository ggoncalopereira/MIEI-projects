using System;
using System.Web;
using System.Threading;
using System.Collections.Generic;
using Mnham_mnham.Estab;
using Mnham_mnham.Utils;
using System.Drawing.Imaging;

namespace Mnham_mnham.Client
{
    class GetImageClientAsyncHandler : IHttpAsyncHandler
    {
        public bool IsReusable { get { return false; } }

        public GetImageClientAsyncHandler()
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
            List<Iguaria> l = ((List<Iguaria>)_context.Session["Items"]);
            int index = int.Parse(_context.Request.QueryString["id"]);
            if (l != null)
            {
                Iguaria s = l.Find(a => a.Id_iguaria==index);
                if (s!=null && s.Fotografia != null)
                {
                    string st = ReturnsImageFormat.ReturnImageFormatString(s.Fotografia);
                    if (st==null)
                    {
                        _context.Response.ContentType = "image/" + st;
                        _context.Response.Write(s.Fotografia);
                    }
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