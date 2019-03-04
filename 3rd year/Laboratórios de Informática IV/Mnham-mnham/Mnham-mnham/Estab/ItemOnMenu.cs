using System;
using System.Drawing.Imaging;
using System.IO;

namespace Mnham_mnham.Estab
{
    public class ItemOnMenu
    {
        public String textBoxItem { get; set; }
        public String textBoxPrice { get; set; }
        public Stream image { get; set; }

        public ImageFormat format { get; set; }

        public String ImgURL { get; set; }


        public ItemOnMenu(String textBoxItem, String textBoxPrice, Stream Image, ImageFormat img)
        {
            this.textBoxItem = textBoxItem;
            this.textBoxPrice = textBoxPrice;
            this.image = Image;
        }


    }
}