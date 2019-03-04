using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Principal;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Mnham_mnham.Client
{
    public partial class MainClient : System.Web.UI.Page
    {
        protected List<Iguaria> pedido;
        protected void Page_Load(object sender, EventArgs e)
        {
            AreaCliente a = (AreaCliente)Session["ClientArea"];
            if(a==null)
            {
                Session["ClientArea"] = new AreaCliente();
            }
            Context.User = (GenericPrincipal)Session["User"];
            if (User != null && !User.IsInRole("Client"))
            {
                Session["User"] = Application["Logout"];
                Context.User = (GenericPrincipal)Session["User"];
            }
        }

        // The return type can be changed to IEnumerable, however to support
        // paging and sorting, the following parameters must be added:
        //     int maximumRows
        //     int startRowIndex
        //     out int totalRowCount
        //     string sortByExpression
        public IQueryable<Iguaria> DvQuery_GetData()
        {
            return pedido.AsQueryable();
        }
    }
}