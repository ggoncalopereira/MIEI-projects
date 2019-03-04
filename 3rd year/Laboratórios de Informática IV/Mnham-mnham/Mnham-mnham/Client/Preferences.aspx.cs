using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Principal;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Mnham_mnham.Client
{
    public partial class Preferences : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Context.User = (GenericPrincipal)Session["User"];
            if (User != null && !User.IsInRole("Client"))
            {
                Session["User"] = Application["Logout"];
                Context.User = (GenericPrincipal)Session["User"];
            }
        }

    }
}