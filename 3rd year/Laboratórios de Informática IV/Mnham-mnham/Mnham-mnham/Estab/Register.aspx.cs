using System;
using System.Linq;
using System.Web;
using System.Web.UI;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Owin;
using Mnham_mnham.Models;
using System.Web.UI.WebControls;
using Business;

namespace Mnham_mnham.Estab
{
    public partial class Register : Page
    {
        protected TextBox City;
        protected TextBox Contact;
        protected TextBox Street;
        protected TextBox Postal;
        protected DropDownList Category;

        protected void Page_Load(object sender, EventArgs e)
        {
            AreaEstabelecimento a = (AreaEstabelecimento)Session["EstabArea"];
            if (a == null)
            {
                Session["EstabArea"] = new AreaEstabelecimento();
            }
        }

        protected void CreateUser_Click(object sender, EventArgs e)
        {
            if (IsValid)
            {
                AreaEstabelecimento es = (AreaEstabelecimento)Session["EstabArea"];

                if (es.RegistarEstabelecimento(Email.Text,Password.Text,null,Nome.Text,Int32.Parse(Contact.Text),null,null,Category.SelectedIndex))
                {
                    Response.Write("Sucesso no registo");
                    Response.Redirect("/Estab/Login");
                }
                else
                {
                    ErrorMessage.Text = "Falha no registo, tente novamente";
                    ErrorMessage.Visible = true;
                }
            }
            else
            {
                ErrorMessage.Text = "Preencha todos os campos";
                ErrorMessage.Visible = true;
            }
        }
    }
}