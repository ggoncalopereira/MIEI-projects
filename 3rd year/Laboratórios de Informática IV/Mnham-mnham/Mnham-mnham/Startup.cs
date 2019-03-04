using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Mnham_mnham.Startup))]
namespace Mnham_mnham
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
