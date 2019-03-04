<%@ Page Title="About" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="About.aspx.cs" Inherits="Mnham_mnham.About" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="panel panel-default">
        <h2><%: Title %>.</h2>
        <h3>Mnham-Mnham é um serviço web de recomendação de pratos/iguarias para a cidade de Braga.</h3>
        <p>Adicione o seu estabelecimento para se juntar aos já registados e divulgar as suas ementas.</p>
    </div>
</asp:Content>

<asp:Content ID="Sidebar" ContentPlaceHolderID="SideBarContent" runat="server">
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

