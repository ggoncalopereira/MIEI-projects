<%@ Page Title="Mnham-Mnham" MasterPageFile="~/Site.Master" Language="C#" AutoEventWireup="true" CodeBehind="Main.aspx.cs" Inherits="Mnham_mnham.Client.Settings" %>


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
    <i class="fa fa-5x fa-edit" style="color: #ffffff"></i>
    </div>
    <div class="col-sm-6">
    <h2 class="fa fa-2x" style="color: #ffffff">Altere as suas definições de utilizador</h2>
    </div>
    <div class="col-sm-3">
    </div>
    </div>    <div class="panel panel-default">
        <p>Nome</p>
        <asp:TextBox ID="TextBox1" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>        
        <i class="fa fa-lg fa-edit"></i>
    </div >
    <div class="panel panel-default">
        <p>Email</p>
        <asp:TextBox ID="TextBox3" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
        <i class="fa fa-lg fa-edit"></i>
    </div>
    <div class="panel panel-default">
        <p>Password</p>
        <asp:TextBox ID="TextBox2" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
        <i class="fa fa-lg fa-edit"></i>
    </div>
    <div>
        <asp:Button ID="Button1" runat="server" Text="Remove Account" CssClass="btn-danger btn" />        
    </div>
</asp:Content>
