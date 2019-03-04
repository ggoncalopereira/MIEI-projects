using System;
using System.Web;
using System.Web.UI;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Owin;
using Mnham_mnham.Models;
using System.Security.Principal;
using Business;

namespace Mnham_mnham.Client
{
    public partial class Login : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Session["ClientArea"] = new AreaCliente();
            RegisterHyperLink.NavigateUrl = "Register";
            var returnUrl = HttpUtility.UrlEncode(Request.QueryString["ReturnUrl"]);
            if (!String.IsNullOrEmpty(returnUrl))
            {
                RegisterHyperLink.NavigateUrl += "?ReturnUrl=" + returnUrl;
            }
        }

        protected void LogIn(object sender, EventArgs e)
        {
            if (IsValid)
            {
                // Validate the user password
                AreaCliente a = (AreaCliente)Session["ClientArea"];
                
                if (a.Login(Email.Text, Password.Text))
                {
                    Session["User"] = new GenericPrincipal(new GenericIdentity(a.AreaClienteCliente.Nome), new string[1] { "Client" });
                    Context.User = (GenericPrincipal)Session["User"];
                    Response.Write("Sucesso no login");
                    Response.Redirect("/Client/Main");
                }
                else
                {
                    FailureText.Text = "Falha no login, confirme os credenciais";
                    ErrorMessage.Visible = true;
                }
            }
            else
            {
                FailureText.Text = "Preencha todos os campos";
                ErrorMessage.Visible = true;
            }
        }
    }
}