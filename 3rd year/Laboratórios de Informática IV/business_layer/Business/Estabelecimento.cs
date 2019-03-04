using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public class Estabelecimento : Utilizador
    {
        private string _ambiente;
        private string _nome;
        private decimal _ratingMedio;
        private int _telefone;
        private int _totalVisualizacoes;
        private List<Horario> _horarios;
        private Dictionary<int, Iguaria> _iguarias;
        private Endereco _endereco;
        private int _id_estabelecimento;
        

        private int _id_categoria;


        public Estabelecimento()
         : base()
        {
            Ambiente = "";
            Nome = "";
            RatingMedio = 0;
            Telefone = 0;
            TotalVisualizacoes = 0;
            _horarios = new List<Horario>();
            _iguarias = new Dictionary<int, Iguaria>();
            Endereco = new Endereco();
            IdEstabelecimento = _id_estabelecimento;
            IdCategoria = _id_categoria;
           
        }

       
        public Estabelecimento(string email, string password, string ambiente, string nome, decimal ratingMedio, int telefone,
            int totalVisualizacoes,
            List<Horario> horarios,
            Endereco endereco,
            int id_estabelecimento,
            int id_categoria)
            : base(email, password, 1)
        {

            Ambiente = ambiente;
            Nome = nome;
            RatingMedio = ratingMedio;
            Telefone = telefone;
            TotalVisualizacoes = totalVisualizacoes;
            Horarios = horarios;
            _iguarias = new Dictionary<int, Iguaria>();
            Endereco = endereco;
            IdEstabelecimento = id_estabelecimento;
            IdCategoria = id_categoria;
          

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


        public string Ambiente
        {
            get
            {

                return _ambiente;
            }
            set
            {
                _ambiente = value;
            }
        }

        public decimal RatingMedio
        {
            get
            {

                return _ratingMedio;
            }
            set
            {
                _ratingMedio = value;
            }
        }

        public int Telefone
        {
            get
            {

                return _telefone;
            }
            set
            {
                _telefone = value;
            }
        }

        public int TotalVisualizacoes
        {
            get
            {

                return _totalVisualizacoes;
            }
            set
            {
                _totalVisualizacoes = value;
            }
        }



        public List<Horario> Horarios
        {

            get
            {


                return _horarios;
            }






            set
            {
                
                   _horarios = new List<Horario>();
                foreach (Horario h in value)
                {

                    _horarios.Add(h.Clone());


                }

            }

        }

        public Dictionary<int, Iguaria> IguariasMap
        {
            get
            {



                return _iguarias;
            }
            set
            {

                _iguarias.Clear();
                foreach (KeyValuePair<int, Iguaria> entry in value)
                {
                    _iguarias.Add(entry.Key, entry.Value.Clone());
                }


            }
        }




        public Endereco Endereco
        {
            get
            {
                return _endereco;
            }
            set
            {
                _endereco = value;
            }
        }

        public int IdEstabelecimento
        {
            get
            {
                return _id_estabelecimento;
            }
            set
            {
                _id_estabelecimento = value;
            }
        }

        public int IdCategoria
        {
            get
            {
                return _id_categoria;
            }
            set
            {
                _id_categoria = value;
            }
        }

      

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();

            
            sb.Append("Nome ........ :").Append(Nome).AppendLine();

            sb.Append("Rating ...... : ").Append(RatingMedio).AppendLine();
            sb.Append("Visualizações : ").Append(TotalVisualizacoes).AppendLine();
            sb.Append("Telefone  ... : ").Append(Telefone).AppendLine();
            sb.Append("Ambiente  ... : ").Append(Ambiente).AppendLine();
            sb.Append(" ... Endereço  ... ").AppendLine();
            sb.Append(this._endereco.ToString()).AppendLine();
            sb.Append("ID : ").Append(IdEstabelecimento).Append(" ").Append("Categoria : ").Append(IdCategoria).AppendLine();
            sb.Append("\n ------------ Horário ------------- \n").AppendLine();
            foreach (var item in _horarios)
            {
                sb.Append(item.ToString());
            }



           
            sb.Append("\n ------------ Iguarias ------------- \n").AppendLine();
            foreach (var item in _iguarias.Values)
            {
                sb.Append(item.ToString());
            }



            return sb.ToString();
        }



    }


}
