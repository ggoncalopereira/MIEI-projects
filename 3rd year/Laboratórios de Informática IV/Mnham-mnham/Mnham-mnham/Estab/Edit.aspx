<%@ Page Title="Mnham-Mnham" MasterPageFile="~/Site.Master" Language="C#" AutoEventWireup="true" CodeBehind="Main.aspx.cs" Inherits="Mnham_mnham.Estab.Edit" %>


<asp:Content runat="server" ID="MainClientSidebar" ContentPlaceHolderID="SideBarContent">
    <li class="sidebar-brand">
        <a class="navbar-brand" runat="server" onclick="Unnamed_LoggingOut" href="~/"><i class="fa fa-pull-left fa-2x fa-home"></i>Sair</a>
    </li>
    <asp:LoginView runat="server" ViewStateMode="Disabled">
        <%-- 
            <RoleGroups>
            <asp:RoleGroup Roles="Estab">
                <ContentTemplate>
        --%>
        <LoggedInTemplate>
            <li>
                <a runat="server" href="~/Estab/Main"><i class="fa fa-pull-left fa-2x fa-plus"></i>Adicionar</a>
            </li>
            <li>
                <a runat="server" href="~/Estab/Edit"><i class="fa fa-pull-left fa-2x fa-edit"></i>Editar</a>
            </li>
            <li>
                <a runat="server" href="~/Estab/Settings"><i class="fa fa-pull-left fa-2x fa-cog"></i>Configurações</a>
            </li>

        </LoggedInTemplate>
        <%-- 
                </ContentTemplate>
            </asp:RoleGroup>
        </RoleGroups>        
        --%>
    </asp:LoginView>



</asp:Content>
<asp:Content runat="server" ID="MainEstab" ContentPlaceHolderID="MainContent">
   <asp:LoginView runat="server" ViewStateMode="Disabled">

        <LoggedInTemplate>
        <asp:GridView ID="gvMenu" runat="server" AutoGenerateColumns="true" AllowPaging="true">
                <Columns>
                    <asp:BoundField DataField="textBoxItem" ItemStyle-CssClass="fa" ReadOnly="true" />
                    <asp:ImageField DataImageUrlField="ImgUrl" ItemStyle-VerticalAlign="Middle"></asp:ImageField>
                    <asp:BoundField DataField="textBoxPrice" ItemStyle-CssClass="fa fa-2x" ReadOnly="true" />
                    <asp:ButtonField ButtonType="Button" CommandName="RemoveItem" ControlStyle-CssClass="fa fa-2x fa-cross btn btn-danger" />
                </Columns>
            </asp:GridView>
        </LoggedInTemplate>

    </asp:LoginView>
</asp:Content>
