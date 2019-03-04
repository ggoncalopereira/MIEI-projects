using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public class Cliente : Utilizador
    {
        private int _id_cliente;
        private string _nome;
        private Preferencias _preferencias;

        public Cliente()
             : base()
        {
            _id_cliente = 0;
            _nome = "";
            _preferencias = new Preferencias();
        }

        public Cliente(string email, string password, string nome, Preferencias preferencias) 
            : base(email, password, 0)
        {
            _id_cliente = 0;
            Nome = nome;
            ListaPreferencias = preferencias;
        }

      

       

        public Cliente(Cliente _other) : base(_other)
        {
            _id_cliente = _other.IdCliente;
            _nome = _other.Nome;
            _preferencias = _other.ListaPreferencias;
        }

        public string Nome
        {
            get
            {

                return _nome;
            }
            set
            {
                _nome = value;
            }
        }

        public Preferencias ListaPreferencias
        {
            get { return _preferencias.Clone(); }
            set
            {
                _preferencias = value.Clone();
            }
        }

       

        public int IdCliente
        {
            get { return _id_cliente; }
            set
            {
                _id_cliente = value;
            }
        }

        public Cliente Clone()
        {
            return new Cliente(this);
        }

        public override string ToString()
        {
            
            StringBuilder sb = new StringBuilder();
            sb.Append(base.ToString());
            sb.Append("ID . : ").Append(IdCliente).AppendLine();
            sb.Append("Nome : ").Append(Nome).AppendLine();
            sb.Append(ListaPreferencias.ToString());
            return sb.ToString();
        }
    }
}