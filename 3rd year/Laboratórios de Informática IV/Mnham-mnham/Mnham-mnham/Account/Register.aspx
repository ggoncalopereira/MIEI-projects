<%@ Page Title="Register" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Register.aspx.cs" Inherits="Mnham_mnham.Account.Register" %>

<asp:Content runat="server" ID="BodyContent" ContentPlaceHolderID="MainContent">
    <h2><%: Title %>.</h2>
    <p class="text-danger">
        <asp:Literal runat="server" ID="ErrorMessage" />
    </p>

    <div class="form-horizontal">
        <h4>Create a new account</h4>
        <hr />
        <asp:ValidationSummary runat="server" CssClass="text-danger" />
        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="Email" CssClass="col-md-2 control-label">Email</asp:Label>
            <div class="col-md-10">
                <asp:TextBox runat="server" ID="Email" CssClass="form-control" TextMode="Email" />
                <asp:RequiredFieldValidator runat="server" ControlToValidate="Email"
                    CssClass="text-danger" ErrorMessage="The email field is required." />
            </div>
        </div>
        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="Password" CssClass="col-md-2 control-label">Password</asp:Label>
            <div class="col-md-10">
                <asp:TextBox runat="server" ID="Password" TextMode="Password" CssClass="form-control" />
                <asp:RequiredFieldValidator runat="server" ControlToValidate="Password"
                    CssClass="text-danger" ErrorMessage="The password field is required." />
            </div>
        </div>
        <div class="form-group">
            <asp:Label runat="server" AssociatedControlID="ConfirmPassword" CssClass="col-md-2 control-label">Confirm password</asp:Label>
            <div class="col-md-10">
                <asp:TextBox runat="server" ID="ConfirmPassword" TextMode="Password" CssClass="form-control" />
                <asp:RequiredFieldValidator runat="server" ControlToValidate="ConfirmPassword"
                    CssClass="text-danger" Display="Dynamic" ErrorMessage="The confirm password field is required." />
                <asp:CompareValidator runat="server" ControlToCompare="Password" ControlToValidate="ConfirmPassword"
                    CssClass="text-danger" Display="Dynamic" ErrorMessage="The password and confirmation password do not match." />
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <asp:Button runat="server" OnClick="CreateUser_Click" Text="Register" CssClass="btn btn-default" />
            </div>
        </div>
    </div>
</asp:Content>

<asp:Content ID="RegisterSidebar" ContentPlaceHolderID="SideBarContent" runat="server">
   <li class="sidebar-brand">
        <a class="navbar-brand" runat="server" href="~/"><i class="fa fa-pull-left fa-2x fa-home"></i>Home</a>
    </li>

</asp:Content>

<%--
<asp:Content runat="server" ID="MainEstab" ContentPlaceHolderID="MainContent">
    <div class="page-header row">
    <div class="col-sm-3">
    <i class="fa fa-5x fa-building" style="color: #ffffff"></i>
    </div>
    <div class="col-sm-6">
    <h2 class="fa fa-2x" style="color: #ffffff">Register your establishment</h2>
    </div>
    <div class="col-sm-3">
    </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="panel" style="background: rgba(255,255,255,0.3)">
                <p>Name</p>
                <asp:TextBox ID="TextBox1" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
            </div>
            <div class="panel" style="background: rgba(255,255,255,0.3)">
                <p class="fa">Image</p>
                <i class="fa fa-lg fa-file-image-o"></i>
                <asp:FileUpload ID="FileUpload1" runat="server" CssClass="btn fa" />
            </div>
            <div class="panel" style="background: rgba(255,255,255,0.3)">
                <p>City</p>
                <asp:TextBox ID="TextBox4" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
            </div>
            <div class="panel" style="background: rgba(255,255,255,0.3)">
                <p>Password</p>
                <asp:TextBox ID="TextBox7" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
            </div>
                <asp:Button ID="Button1" runat="server" CssClass="btn-danger" Text="Remove Account"></asp:Button>
                <i class="fa fa-lg fa-edit"></i>
        </div>
        <div class="col-sm-6">
            <div class="panel" style="background: rgba(255,255,255,0.3)">
                <p>Contact</p>
                <asp:TextBox ID="TextBox2" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
            </div>
            <div class="panel" style="background: rgba(255,255,255,0.3)">
                <p>Street</p>
                <asp:TextBox ID="TextBox3" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
            </div>
            <div class="panel" style="background: rgba(255,255,255,0.3)">
                <p>Postal Code</p>
                <asp:TextBox ID="TextBox5" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
            </div>
            <div class="panel" style="background: rgba(255,255,255,0.3)">
                <p>Email</p>
                <asp:TextBox ID="TextBox6" runat="server" CssClass="fa" Height="31px" Width="1200px"></asp:TextBox>
                <i class="fa fa-lg fa-edit"></i>
            </div>
        </div>
    </div>--%>

</asp:Content>
