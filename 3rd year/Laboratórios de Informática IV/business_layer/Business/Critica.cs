using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public class Critica
    {
        private string _descricao;
        private DateTime _data;
        private decimal _rating;

        public Critica()
        {
            throw new System.NotImplementedException();
        }

        public Critica(string descricao, DateTime data, decimal rating)
        {
            DescricaoCritica = descricao;
            DataCritica = data;
            RatingIguaria = rating;
        }
        public Critica(Critica _other)
        {
            _descricao = _other.DescricaoCritica;
            _data = _other.DataCritica;
            _rating = _other.RatingIguaria;
        }

        public string DescricaoCritica
        {
            get
            {

                return _descricao;
            }
            set
            {

                _descricao = value;
            }
        }

        public DateTime DataCritica
        {
            get
            {

                return _data;
            }
            set
            {
                _data = value;
            }
        }

        public decimal RatingIguaria
        {
            get
            {

                return _rating;
            }
            set
            {
                _rating = value;
            }
        }

        public Critica Clone()
        {
            return new Critica(this);
        }


        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("Data .... : ").Append(DataCritica).AppendLine();
            sb.Append("Rating .. : ").Append(RatingIguaria).AppendLine();
            sb.Append("Descrição : ").Append(DescricaoCritica).AppendLine();
            sb.AppendLine();

            return sb.ToString();
        }

    }
}