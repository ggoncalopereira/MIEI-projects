using System;
using System.Linq;
using System.Web;
using System.Web.UI;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Owin;
using Mnham_mnham.Models;
using Business;

namespace Mnham_mnham.Client
{
    public partial class Register : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            AreaCliente a = (AreaCliente)Session["ClientArea"];
            if(a==null)
            {
                Session["ClientArea"] = new AreaCliente();
            }
        }

        protected void CreateUser_Click(object sender, EventArgs e)
        {
            if(IsValid)
            {
                AreaCliente a = (AreaCliente)Session["ClientArea"];
                if (a.RegistarCliente(Email.Text, Password.Text, Nome.Text))
                {
                    Response.Write("Sucesso no registo");
                    Response.Redirect("/Client/Login");
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