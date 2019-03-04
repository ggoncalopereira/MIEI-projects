using Mnham_mnham.Security;
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
    public partial class MainEstab : System.Web.UI.Page
    {
        protected TextBox TextBoxItem;
        protected TextBox TextBoxPrice;
        protected FileUpload FileUploadImage;
        protected ImageFormat FileFormat;


        protected void Page_Load(object sender, EventArgs e)
        {
            Context.User = (GenericPrincipal)Session["User"];
            if (User != null && !User.IsInRole("Estab"))
            {
                Session["User"] = Application["Logout"];
                Context.User = (GenericPrincipal)Session["User"];
            }
            FileUploadImage = new FileUpload();

            if(Session["Items"]==null)
            {
                //GetFromDB
                Session["Items"] = new List<ItemOnMenu>();
            }
        }

        protected void UpdateCache(object sender, EventArgs e)
        {
            if (FileUploadImage.HasFile &&
                FileUploadValidator.FileIsWebFriendlyImage(FileUploadImage.FileContent, 2000000, out FileFormat))
            {

                if (IsValid)
                {
                    //Write to DB then
                    List<ItemOnMenu> E = (List<ItemOnMenu>)Session["Items"];
                    E.Add(new ItemOnMenu(TextBoxItem.Text, TextBoxPrice.Text, FileUploadImage.FileContent, FileFormat));
                    Response.Write("Sucesso");
                    Response.StatusCode = 200;
                    Response.Redirect("/Estab/Main");
                }
                else
                {
                    Response.Write("Preenchimento Inválido");
                    Response.StatusCode = 400;
                }
            }
        }

        protected void ForgetRequest(object sender, EventArgs e)
        {
            Response.Redirect("/Estab/Main");
            TextBoxItem.Text = "";
            TextBoxPrice.Text = "";
            FileUploadImage = new FileUpload();
        }

        protected void LogoutEstablishment(object sender, EventArgs e)
        {
            //Trash Current Session
            Session.Abandon();
            //Logout somehow
            Context.User = new GenericPrincipal(new GenericIdentity(null), null);

        }
    }
}