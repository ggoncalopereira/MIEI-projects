using System;
using System.Web;
using System.Web.UI;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Owin;
using Mnham_mnham.Models;
using System.Security.Principal;
using Business;

namespace Mnham_mnham.Estab
{
    public partial class Login : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            RegisterHyperLink.NavigateUrl = "Register";
            // Enable this once you have account confirmation enabled for password reset functionality
            //ForgotPasswordHyperLink.NavigateUrl = "Forgot";
            //OpenAuthLogin.ReturnUrl = Request.QueryString["ReturnUrl"];
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
                AreaEstabelecimento a = (AreaEstabelecimento)Session["EstabArea"];

                if (a.Login(Email.Text, Password.Text))
                {
                    Session["User"] = new GenericPrincipal(new GenericIdentity(a.AreaEstabelecimentoEstabelecimento.Nome), new string[1] { "Estab" });
                    Context.User = (GenericPrincipal)Session["User"];
                    Response.Write("Sucesso no login");
                    Response.Redirect("/Estab/Main");
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