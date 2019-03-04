using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public class Utilizador
    {
        private string _email;
        private string _password;
        private byte _tipo;

        public Utilizador()
        {
            this._email = "";
            this._password = "";
            this._tipo = 0;
        }

        public Utilizador(string email, string password, byte tipo)
        {
            Email = email;
            Password = password;
            Tipo = tipo;
        }
        public Utilizador(Utilizador _other)
        {
            _email = _other.Email;
            _password = _other.Password;
            _tipo = _other.Tipo;
        }

        public string Email
        {
            get
            {
                return _email;
            }
            set
            {
                _email = value;
            }
        }

        public string Password
        {
            get
            {
                return _password;

            }
            set
            {
                _password = value;
            }
        }

        public byte Tipo
        {
            get
            {

                return _tipo;
            }
            set
            {
                _tipo = value;
            }
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();

            if (Tipo == 0)
                sb.Append("---------  Cliente  ---------").AppendLine();
            else
                sb.Append("------ Estabelecimento ------").AppendLine();
            sb.Append("Email: ").Append(Email).AppendLine();


            return sb.ToString();
        }


    }
}