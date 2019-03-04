using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;

namespace Mnham_mnham.Utils
{
    public static class StreamConversion
    {
        public static byte[] StreamToByteArray(Stream image)
        {
            if (image is MemoryStream)
            {
                return ((MemoryStream)image).ToArray();
            }
            else
            {
                using (MemoryStream ms = new MemoryStream())
                {
                    image.CopyTo(ms);
                    return ms.ToArray();
                }
            }
        }
    }
}