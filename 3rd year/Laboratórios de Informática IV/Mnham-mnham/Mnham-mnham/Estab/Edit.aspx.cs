using System;
using System.Collections.Generic;
using System.Drawing.Imaging;
using System.Linq;
using System.Security.Principal;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Mnham_mnham.Estab
{
    public partial class Edit : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Context.User = (GenericPrincipal)Session["User"];
            if (User!=null && !User.IsInRole("Estab"))
            {
                Session["User"] = Application["Logout"];
                Context.User = (GenericPrincipal)Session["User"];
            }
            gvMenu = new GridView();
            if (Session["Items"] == null)
            {
                //Load from DB to Session
                Session["Items"] = new List<ItemOnMenu>
                {
                    new ItemOnMenu("1", "1", null, ImageFormat.Bmp),
                    new ItemOnMenu("1", "1", null, ImageFormat.Bmp),
                    new ItemOnMenu("1", "1", null, ImageFormat.Bmp),
                    new ItemOnMenu("1", "1", null, ImageFormat.Bmp),
                    new ItemOnMenu("1", "1", null, ImageFormat.Bmp),
                    new ItemOnMenu("1", "1", null, ImageFormat.Bmp),
                    new ItemOnMenu("1", "1", null, ImageFormat.Bmp),
                    new ItemOnMenu("1", "1", null, ImageFormat.Bmp)
                };
            }
            List<ItemOnMenu> l = (List<ItemOnMenu>)Session["Items"];
            if(l!=null)
            {
                int index = 0;
                foreach (var item in l)
                {
                    item.ImgURL = "GetImg.ImgGetClient?id=" + index;
                    index++;
                }
                gvMenu.DataSource = l;
                gvMenu.DataBind();
            }
        }


    }
}