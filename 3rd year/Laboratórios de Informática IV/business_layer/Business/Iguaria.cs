using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public class Iguaria
    {
        private string _nome;
        private int _visualizacoes;
        private decimal _rating_medio;
        private byte[] _fotografia;
        private decimal _preco;
        private int _id_iguaria;
        private int _id_estabelecimento;
        private List<Critica> _criticas;
        private IguariaStatus _crud_status;
        private decimal _distancia;


        public Iguaria()
        {
            throw new System.Exception("Not implemented");
        }





        public Iguaria(string nome, int visualizacoes, decimal rating_medio, byte[] fotografia, decimal preco, int id_iguaria,
            int id_estabelecimento)
        {
            this._nome = nome;
            this._visualizacoes = visualizacoes;
            this._rating_medio = rating_medio;
            this._fotografia = fotografia;
            this._preco = preco;
            this._id_iguaria = id_iguaria;
            this._id_estabelecimento = id_estabelecimento;
            this._criticas = new List<Critica>();
            this._crud_status = IguariaStatus.Default;
            Distancia = 0;
        }

        public Iguaria(string nome, int visualizacoes, decimal rating_medio, byte[] fotografia, decimal preco, int id_iguaria, int id_estabelecimento,
            List<Critica> criticas)
        {
            this._nome = nome;
            this._visualizacoes = visualizacoes;
            this._rating_medio = rating_medio;
            this._fotografia = fotografia;
            this._preco = preco;
            this._id_iguaria = id_iguaria;
            this._id_estabelecimento = id_estabelecimento;
            this._criticas = criticas;
            this._crud_status = IguariaStatus.Default;
            Distancia = 0;
        }

        public Iguaria(Iguaria _other)
        {
            this._nome = _other.Nome;
            this._visualizacoes = _other.VisualizacoesIguaria;
            this._rating_medio = _other.RatingMedioIguaria;
            this._fotografia = _other.Fotografia;
            this._preco = _other.Preco;
            this._id_iguaria = _other.IdIguaria;
            this._id_estabelecimento = _other.IdEstabelecimento;
            this._criticas = _other.ListaCriticas;
            this._crud_status = _other.CrudStatus;
            this._distancia = _other.Distancia;
        }

        public Iguaria(string nome, byte[] fotografia, decimal preco, int id_iguaria, int idEstabelecimento, IguariaStatus crudStatus)
        {
            this._nome = nome;
            this._fotografia = fotografia;
            this._preco = preco;
            this._id_iguaria = id_iguaria;
            this._id_estabelecimento = idEstabelecimento;
            this._crud_status = crudStatus;
        }

        public string Nome
        {
            get
            {

                return _nome;
            }
            set
            {
            }
        }

        public int VisualizacoesIguaria
        {
            get
            {

                return _visualizacoes;
            }
            set
            {
            }
        }

        public decimal RatingMedioIguaria
        {
            get
            {

                return _rating_medio;
            }
            set
            {
                _rating_medio = value;
            }
        }



        public decimal Preco
        {
            get
            {

                return _preco;
            }
            set
            {
            }
        }


        public byte[] Fotografia
        {



            get
            {

                byte[] tmp = new byte[_fotografia.Length];
                for (int i = 0; i < _fotografia.Length; i++)
                {
                    tmp[i] = _fotografia[i];
                }


                return tmp;


            }




            set
            {
                Array.Clear(_fotografia, 0, _fotografia.Length);
                for (int i = 0; i < value.Length; i++)
                {
                    _fotografia[i] = value[i];
                }






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

        public int IdIguaria
        {
            get
            {

                return _id_iguaria;
            }
            set
            {
                _id_iguaria = value;
            }
        }

        public List<Critica> ListaCriticas
        {
            get
            {

                return _criticas;
            }

            set
            {
                _criticas.Clear();
                foreach (Critica c in value)
                {
                    _criticas.Add(c.Clone());
                }

            }
        }

        public IguariaStatus CrudStatus
        {
            get
            {

                return _crud_status;

            }


            set
            {
                _crud_status = value;
            }
        }

        public decimal Distancia
        {
            get
            {

                return _distancia;
            }
            set
            {
                _distancia = value;
            }
        }

        public Iguaria Clone()
        {
            return new Iguaria(this);
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();


            sb.Append("Nome ........ : ").Append(Nome).AppendLine();
            sb.Append("Preço  ...... : ").Append(Preco).AppendLine();
            sb.Append("Rating ...... : ").Append(RatingMedioIguaria).AppendLine();
            sb.Append("Visualizações : ").Append(VisualizacoesIguaria).AppendLine();
            sb.Append("Fotografia .. : ").Append(Fotografia.Length).Append(" bytes ").AppendLine();
            if (Distancia > 0)
            {
                sb.Append("Distância .... : ").Append(Distancia.ToString("0.##")).Append(" m ").AppendLine();
            }
            sb.Append("ID : ").Append(IdIguaria).Append(" ").Append("Estabelecimento : ").Append(IdEstabelecimento).AppendLine();
            if (_criticas.Count > 0)
            {
                sb.Append("\n ------------ Criticas ------------- \n").AppendLine();
                foreach (var item in _criticas)
                {
                    sb.Append(item.ToString());
                }
            }




            return sb.ToString();
        }
    }
}