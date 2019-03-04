<%@ Page Title="Mnham-Mnham" MasterPageFile="~/Site.Master" Language="C#" AutoEventWireup="true" CodeBehind="Main.aspx.cs" Inherits="Mnham_mnham.Estab.MainEstab" %>


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
       <%-- <RoleGroups>
            <asp:RoleGroup Roles="Estab">
                <ContentTemplate>
                            --%>

        <LoggedInTemplate>
                    <div class="page-header row">
                        <div class="col-sm-3">
                            <i class="fa fa-5x fa-book" style="color: #ffffff"></i>
                        </div>
                        <div class="col-sm-6">
                            <h2 class="fa fa-2x" style="color: #ffffff">Adicone um item de ementa</h2>
                        </div>
                        <div class="col-sm-3">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                        </div>
                        <div class="col-sm-6">
                            <div class="panel" style="background: rgba(255,255,255,0.3)">
                                <p>Item</p>
                                <asp:TextBox ID="TextBoxItem" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                                <i class="fa fa-lg fa-edit"></i>
                                <asp:RequiredFieldValidator ControlToValidate="TextBoxItem" ErrorMessage="Introduza o nome do Item" runat="server" CssClass="text-danger fa"></asp:RequiredFieldValidator>
                            </div>
                            <div class="panel" style="background: rgba(255,255,255,0.3)">
                                <p>Preço</p>
                                <asp:TextBox ID="TextBoxPrice" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                                <i class="fa fa-lg fa-edit"></i>
                                <asp:RequiredFieldValidator ControlToValidate="TextBoxPrice" ErrorMessage="Introduza o preço do Item" runat="server" CssClass="text-danger fa"></asp:RequiredFieldValidator>
                            </div>
                            <div class="panel" style="background: rgba(255,255,255,0.3)">
                                <p class="fa">Imagem</p>
                                <i class="fa fa-lg fa-file-image-o"></i>
                                <asp:FileUpload ID="FileUploadImage" runat="server"  CssClass="btn fa" />
                                <asp:RequiredFieldValidator ControlToValidate="FileUploadImage" ErrorMessage="Carregue imagem com menos de 2MB, .png, .jpeg ou .gif" runat="server" CssClass="text-danger fa"></asp:RequiredFieldValidator>
                            </div>
                            <div class="panel" style="background: rgba(255,255,255,0.3)">
                                <asp:Button ID="Button1" runat="server" OnClick="UpdateCache" CssClass="btn fa" Text="Submeter"></asp:Button>
                            </div> 
                            <div class="panel" style="background: rgba(255,255,255,0.3)">
                                <asp:Button ID="Button2" runat="server" OnClick="ForgetRequest" CssClass="btn fa" Text="Cancelar"></asp:Button>
                                <i class="fa fa-2x fa-cross"></i>
                            </div>
                        </div>
                    </div>
        </LoggedInTemplate>
        <%--
                </ContentTemplate>
            </asp:RoleGroup>
        </RoleGroups>   
        --%>
    </asp:LoginView>

</asp:Content>
