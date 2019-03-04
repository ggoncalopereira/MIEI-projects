<%@ Page Title="Mnham-Mnham" MasterPageFile="~/Site.Master" Language="C#" AutoEventWireup="true" CodeBehind="Main.aspx.cs" Inherits="Mnham_mnham.Client.Preferences" %>


<asp:Content runat="server" ID="MainClientSidebar" ContentPlaceHolderID="SideBarContent">
    <li class="sidebar-brand">
        <a class="navbar-brand" runat="server" href="~/" onclick="Unnamed_LoggingOut"><i class="fa fa-pull-left fa-2x fa-home"></i>Sair</a>
    </li>
    <asp:LoginView runat="server" ViewStateMode="Disabled">
        <AnonymousTemplate>
            <li><a runat="server" href="~/Client/Register"><i class="fa fa-pull-left fa-2x fa-group"></i>Registo</a></li>
            <li><a runat="server" href="~/Client/Login"><i class="fa fa-pull-left fa-2x fa-laptop"></i>Entrada</a></li>
        </AnonymousTemplate>
        <LoggedInTemplate>
            <li><a runat="server" href="~/Client/History"><i class="fa fa-pull-left fa-2x fa-history"></i>Histórico</a></li>
    <li>
        <a runat="server" href="~/Client/Preferences"><i class="fa fa-pull-left fa-2x fa-heart"></i>Preferências</a>
    </li>
            <li><a runat="server" href="~/Client/Settings"><i class="fa fa-pull-left fa-2x fa-cog"></i>Configurações</a></li>

        </LoggedInTemplate>
    </asp:LoginView>

</asp:Content>

<asp:Content runat="server" ID="MainContentClient" ContentPlaceHolderID="MainContent">
    <div class="page-header row">
    <div class="col-sm-3">
    <i class="fa fa-5x fa-heart" style="color: #ffffff"></i>
    </div>
    <div class="col-sm-6">
    <h2 class="fa fa-2x" style="color: #ffffff">Escolha as suas preferências de pesquisa</h2>
    </div>
    <div class="col-sm-3">
    </div>
    </div>
    <div class="jumbotron form-group-hg">
        <asp:CheckBoxList ID="CheckBoxList1" runat="server" CssClass="checkbox fa fa-3x" ForeColor="DarkBlue">
            <asp:ListItem>Distância</asp:ListItem>
            <asp:ListItem>Avaliação Iguaria</asp:ListItem>
            <asp:ListItem>Avaliação Estabelecimento</asp:ListItem>
            <asp:ListItem>Popularidade Estabelecimento</asp:ListItem>
            <asp:ListItem>Popularidade Iguaria</asp:ListItem>
        </asp:CheckBoxList>

    </div>
</asp:Content>
