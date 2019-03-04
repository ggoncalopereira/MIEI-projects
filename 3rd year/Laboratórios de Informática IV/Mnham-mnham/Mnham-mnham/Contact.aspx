<%@ Page Title="Contactos" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Contact.aspx.cs" Inherits="Mnham_mnham.Contact" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="panel panel-default">
        <h2><%: Title %>.</h2>
        <h3>Contacte-nos em:</h3>
        <address>
            Universidade do Minho, Campus de Gualtar<br />
            Departamento de informática<br />
        </address>

        <address>
            <strong>Suporte:</strong>   <a href="mailto:suporte@mnham-mnham.xyz">suporte@mnham-mnham.xyz</a><br />
        </address>
    </div>
</asp:Content>

<asp:Content ID="ContactSidebar" ContentPlaceHolderID="SideBarContent" runat="server">
    <li class="sidebar-brand">
        <a class="navbar-brand" runat="server" href="~/"><i class="fa fa-pull-left fa-2x fa-home"></i>Início</a>
    </li>
    <li>
        <a runat="server" href="~/About"><i class="fa fa-pull-left fa-2x fa-id-card"></i>Sobre nós</a>
    </li>
    <li>
        <a runat="server" href="~/Contact"><i class="fa fa-pull-left fa-2x fa-address-book"></i>Contactos</a>
    </li>
    <li>
        <a runat="server" href="~/Client/Main"><i class="fa fa-pull-left fa-2x fa-cutlery"></i>Mnham-Mnham</a>
    </li>
    <li>
        <a runat="server" href="~/Estab/Login"><i class="fa fa-pull-left fa-2x fa-cubes"></i>Estabelecimentos</a>
    </li>


</asp:Content>

