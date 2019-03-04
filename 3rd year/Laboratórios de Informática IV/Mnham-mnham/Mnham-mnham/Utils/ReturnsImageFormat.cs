using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Web;

namespace Mnham_mnham.Utils
{
    public static class ReturnsImageFormat
    {
        public static string ReturnImageFormatString(ImageFormat f)
        {
            string format;
            if (f.Equals(ImageFormat.Png))
            {
                format = "png";
            }
            else if (f.Equals(ImageFormat.Jpeg))
            {
                format = "jpeg";
            }
            else if (f.Equals(ImageFormat.Gif))
            {
                format = "gif";
            }
            else
            {
                format = null;
            }
            return format;
        }

        internal static string ReturnImageFormatString(byte[] fotografia)
        {
            string s="";
            MemoryStream stream = new MemoryStream(fotografia);

            var i = Image.FromStream(stream);

            //Move the pointer back to the beginning of the stream
            stream.Seek(0, SeekOrigin.Begin);

            if (ImageFormat.Png.Equals(i.RawFormat))
            {
                s = "png";               
            }
            else if (ImageFormat.Gif.Equals(i.RawFormat))
            {
                s = "gif";
            }
            else if (ImageFormat.Jpeg.Equals(i.RawFormat))
            {
                s = "jpg";
            }
            return s;
        }

        internal static ImageFormat ReturnImageFormat(byte[] fotografia)
        {
            ImageFormat format = null;
            MemoryStream stream = new MemoryStream(fotografia);

            var i = Image.FromStream(stream);

            //Move the pointer back to the beginning of the stream
            stream.Seek(0, SeekOrigin.Begin);

            if (ImageFormat.Png.Equals(i.RawFormat))
            {
                format = ImageFormat.Png;
            }
            else if (ImageFormat.Gif.Equals(i.RawFormat))
            {
                format = ImageFormat.Gif;
            }
            else if (ImageFormat.Jpeg.Equals(i.RawFormat))
            {
                format = ImageFormat.Jpeg;
            }
            return format;
        }
    }
}