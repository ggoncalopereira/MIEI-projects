using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public class Endereco
    {
        private string _cod_postal;
        private decimal _latitude;
        private string _localidade;
        private decimal _longitude;
        private int _numero;
        private string _rua;

        public Endereco()
        {
            CodPostal = "";
            Latitude = 0;
            Localidade = "";
            Longitude = 0;
            Numero = 0;
            Rua = "";
        }

        public Endereco(string _cod_postal, decimal _latitude, string _localidade, decimal _longitude, int _numero, string _rua)
        {
            CodPostal = _cod_postal;
            Latitude = _latitude;
            Localidade = _localidade;
            Longitude = _longitude;
            Numero = _numero;
            Rua = _rua;
        }


        public Endereco(string _cod_postal, string _localidade, int _numero, string _rua)
        {
            CodPostal = _cod_postal;
            Latitude = 0;
            Localidade = _localidade;
            Longitude = 0;
            Numero = _numero;
            Rua = _rua;
        }

        public Endereco(Endereco _other)
        {
            _cod_postal = _other.CodPostal;
            _latitude = _other.Latitude;
            _localidade = _other.Localidade;
            _longitude = _other.Longitude;
            _numero = _other.Numero;
            _rua = _other.Rua;

        }

        public decimal Latitude
        {
            get
            {

                return _latitude;
            }
            set
            {
                _latitude = value;
            }
        }

        public decimal Longitude
        {
            get
            {

                return _longitude;
            }
            set
            {
                _longitude = value;
            }
        }

        public string Rua
        {
            get
            {

                return _rua;
            }
            set
            {
                _rua = value;
            }
        }

        public int Numero
        {
            get
            {

                return _numero;
            }
            set
            {
                _numero = value;
            }
        }

        public string Localidade
        {
            get
            {

                return _localidade;
            }
            set
            {
                _localidade = value;
            }
        }

        public string CodPostal
        {
            get
            {

                return _cod_postal;
            }
            set
            {
                _cod_postal = value;
            }
        }

        public Endereco Clone()
        {
            return new Endereco(this);
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();


            sb.Append("Rua ...... : " ).Append(Rua).AppendLine();
            sb.Append("Número ... : " ).Append(Numero).AppendLine();
            sb.Append("Cód-Postal : " ).Append(CodPostal).AppendLine();
            sb.Append("Localidade : " ).Append(Localidade).AppendLine();
            sb.Append(new GPSVal(Convert.ToDouble(Latitude), Convert.ToDouble(Longitude)).ToString());
            

            return sb.ToString();
        }
    }
}