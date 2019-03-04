using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public class Direcoes
    {
        private List<string> _lista_direcoes;

        public Direcoes()
        {
            this._lista_direcoes = new List<string>();

        }


        public List<string> ListaDirecoes
        {
            get
            {

             
                return _lista_direcoes;

            }
            set
            {
                _lista_direcoes.Clear();

                foreach (string item in value)
                {
                    _lista_direcoes.Add(item);
                }

            }
        }

        public void AdicionarDirecao(string direcao)
        {
            _lista_direcoes.Add(direcao);
        }


        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("----- Direções -----").AppendLine();
            foreach (var item in _lista_direcoes)
            {
                sb.Append(item).AppendLine();
            }
            sb.AppendLine();

            return sb.ToString();
        }

    }
}