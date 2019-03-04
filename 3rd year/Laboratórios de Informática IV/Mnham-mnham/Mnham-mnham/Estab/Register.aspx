<%@ Page Title="Register" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Register.aspx.cs" Inherits="Mnham_mnham.Estab.Register" %>

<asp:Content ID="RegisterSidebar" ContentPlaceHolderID="SideBarContent" runat="server">
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

<asp:Content runat="server" ID="MainEstab" ContentPlaceHolderID="MainContent">
    <div class="page-header row">
        <div class="col-sm-3">
            <i class="fa fa-5x fa-building" style="color: #ffffff"></i>
        </div>
        <div class="col-sm-6">
            <h2 class="fa fa-2x" style="color: #ffffff">Registe o seu estabelecimento.</h2>
            <p class="text-danger">
                <asp:Literal runat="server" ID="ErrorMessage" />
            </p>
        </div>
        <div class="col-sm-3">
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p>Nome</p>
                <asp:TextBox ID="Nome" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>                
                <asp:RequiredFieldValidator runat="server" ControlToValidate="Nome"
                    CssClass="fa fa-lg text-danger" ErrorMessage="O nome é obrigatório." />
            </div>
<%--             
    <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p class="fa">Image</p>
                <i class="fa fa-lg fa-file-image-o"></i>
                <asp:FileUpload ID="FileUploadImage" runat="server" CssClass="btn fa" />
                <asp:RequiredFieldValidator runat="server" ControlToValidate="FileUploadImage"
                    CssClass="fa fa-lg text-danger" ErrorMessage="The City field is required." />            
    </div>
--%>
            <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p>Cidade</p>
                <asp:TextBox ID="City" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>                
                <asp:RequiredFieldValidator runat="server" ControlToValidate="City"
                    CssClass="fa fa-lg text-danger" ErrorMessage="A cidade é obrigatória." />
            </div>
            <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p>Email</p>
                <asp:TextBox runat="server" ID="Email" CssClass="form-control" TextMode="Email" />
                <i class="fa fa-lg fa-edit"></i>
                <asp:RequiredFieldValidator runat="server" ControlToValidate="Email"
                    CssClass="fa fa-lg text-danger" ErrorMessage="O email é obrigatório." />
            </div>
            <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p>Password</p>
                <asp:TextBox ID="Password" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
                <asp:RequiredFieldValidator runat="server" ControlToValidate="Password"
                    CssClass="fa fa-lg text-danger" ErrorMessage="A password é obrigatória." />
            </div>
            <asp:Button ID="Button1" runat="server" CssClass="btn btn-default" OnClick="CreateUser_Click" Text="Criar Conta"></asp:Button>
        </div>
        <div class="col-sm-6">
            <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p>Contacto Telefónico</p>
                <asp:TextBox ID="Contact" runat="server" CssClass="fa form-control" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
                <asp:RequiredFieldValidator runat="server" ControlToValidate="Contact"
                    CssClass="fa fa-lg text-danger" ErrorMessage="O contacto é obrigatório." />
            </div>
            <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p>Rua</p>
                <asp:TextBox ID="Street" runat="server" CssClass="fa form-control" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>                
                <asp:RequiredFieldValidator runat="server" ControlToValidate="Street"
                    CssClass="fa fa-lg text-danger" ErrorMessage="A rua é obrigatória." />
            </div>
            <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p>Código Postal</p>
                <asp:TextBox ID="Postal" runat="server" CssClass="fa form-control" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
                <asp:RequiredFieldValidator runat="server" ControlToValidate="Postal"
                    CssClass="fa fa-lg text-danger" ErrorMessage="O código postal é obrigatório." />
            </div>
            <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p>Confirmar Password</p>
                <asp:TextBox runat="server" ID="ConfirmPassword" TextMode="Password" CssClass="fa form-control" />
                <i class="fa fa-lg fa-edit"></i>
                <asp:RequiredFieldValidator runat="server" ControlToValidate="ConfirmPassword"
                    CssClass="fa fa-lg text-danger" Display="Dynamic" ErrorMessage="Tem de confirmar a password." />
                <asp:CompareValidator runat="server" ControlToCompare="Password" ControlToValidate="ConfirmPassword"
                    CssClass="fa fa-lg text-danger" Display="Dynamic" ErrorMessage="As passwords têm de ser iguais." />
            </div>
            <div class="panel form-group" style="background: rgba(255,255,255,0.3)">
                <p>Categoria</p>
                <asp:DropDownList ID="Category" runat="server" CssClass="fa dropdown-toggle" Height="31px" Width="200px">
                    <asp:ListItem Value="0">
                        Restaurante
                    </asp:ListItem>
                    <asp:ListItem Value="1">
                        Confeitaria
                    </asp:ListItem>
                    <asp:ListItem Value="2">
                        Tasca
                    </asp:ListItem>
                </asp:DropDownList>
                <i class="fa fa-lg fa-edit"></i>
                <asp:RequiredFieldValidator runat="server" ControlToValidate="Category"
                    CssClass="fa fa-lg text-danger" ErrorMessage="A categoria é obrigatória." />
            </div>

        </div>
    </div>

</asp:Content>
