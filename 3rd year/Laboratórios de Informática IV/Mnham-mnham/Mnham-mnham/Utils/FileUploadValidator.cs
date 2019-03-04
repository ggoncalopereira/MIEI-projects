using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Web;

namespace Mnham_mnham.Security
{
    /// 
    /// Utility class used to validate the contents of uploaded files
    /// 
    public static class FileUploadValidator
    {
        public static bool FileIsWebFriendlyImage(System.IO.Stream stream, long size, out ImageFormat format)
        {
            bool formatRecognizedAndValidSize = stream.Length <= size;
            format = null;
            if (formatRecognizedAndValidSize)
            {

                try
                {

                    //Read an image from the stream...
                    var i = Image.FromStream(stream);

                    //Move the pointer back to the beginning of the stream
                    stream.Seek(0, SeekOrigin.Begin);

                    if (ImageFormat.Png.Equals(i.RawFormat))
                    {
                        format = ImageFormat.Png;
                        formatRecognizedAndValidSize = true;
                    }
                    else if (ImageFormat.Gif.Equals(i.RawFormat))
                    {
                        format = ImageFormat.Gif;
                        formatRecognizedAndValidSize = true;
                    }
                    else if (ImageFormat.Jpeg.Equals(i.RawFormat))
                    {
                        format = ImageFormat.Jpeg;
                        formatRecognizedAndValidSize = true;
                    }
                }
                catch
                {
                }
            }
            return formatRecognizedAndValidSize;
        }


    }
}