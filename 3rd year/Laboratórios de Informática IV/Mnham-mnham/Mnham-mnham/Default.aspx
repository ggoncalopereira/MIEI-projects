<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="Mnham_mnham._Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="row jumbotron">
        <h1>Mnham-Mnham</h1>
        <p class="lead">Mnham-Mnham é um serviço de recomendações gastronómicas. 
            Registe-se e diga o que lhe vem à mente e ao estômago e recomendações ser-lhe-ão dadas bem como direções para o local.</p>
        <a href="~/Client/Register" runat="server" class="btn btn-default">Registe-se agora &raquo;</a>
    </div>

    <div class="row jumbotron">
        <div class="col-md-5">
            <h2>Para Estabelecimentos</h2>
            <p>
                Mnham-Mnham depende de informação submetida diretamente por si, o estabelecimento.
                Registe o seu estabelecimento e deixe-nos as suas informações e ementa e seja recomendado aos nossos utilizadores.
            </p>
            <p>
                <a class="btn btn-default" runat="server" href="~/Estab/Register">Registe-se agora &raquo;</a>
            </p>
        </div>
        <div class="col-md-2">
        </div>
        <div class="col-md-5">
            <h2>Contactos</h2>
            <p>
                Contacte-nos a qualquer altura.
            </p>
            <p>
                <a class="btn btn-default" runat="server" href="~/Contact">Contacte-nos &raquo;</a>
            </p>
        </div>
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
