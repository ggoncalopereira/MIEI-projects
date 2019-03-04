using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public class Horario
    {
        private Dias _dia;
        private TimeSpan _hora_abertura;
        private TimeSpan _hora_fecho;

        public Horario()
        {
            throw new System.Exception("Not implemented");
        }

        public Horario(byte _dia, TimeSpan _hora_abertura, TimeSpan _hora_fecho)
        {
            Dia = (Dias)_dia;
            HoraAbertura = _hora_abertura;
            HoraFecho = _hora_fecho;
        }
        public Horario(Horario _other)
        {
            this._dia = _other.Dia;
            this._hora_abertura = _other.HoraAbertura;
            this._hora_fecho = _other.HoraFecho;
        }

        public Dias Dia
        {
            get
            {
                return _dia;
            }
            set
            {
                _dia = value;
            }
        }

        public TimeSpan HoraAbertura
        {
            get
            {
                return _hora_abertura;
            }
            set
            {
                _hora_abertura = value;
            }
        }

        public TimeSpan HoraFecho
        {
            get
            {
                return _hora_fecho;
            }
            set
            {
                _hora_fecho = value;
            }
        }

        public Horario Clone()
        {
            return new Horario(this);
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();

            string[] dia = Dia.ToString().Split('_');
           
            if (dia.Length > 1)
                sb.Append(dia[0]).Append("-").Append(dia[1]).Append("\tDas ").Append(HoraAbertura.ToString(@"hh\:mm\:ss")).Append(" às ").Append(HoraFecho.ToString(@"hh\:mm\:ss")).AppendLine();
            else
                sb.Append(dia[0]).Append("\t\tDas ").Append(HoraAbertura.ToString(@"hh\:mm\:ss")).Append(" às ").Append(HoraFecho.ToString(@"hh\:mm\:ss")).AppendLine();


            return sb.ToString();
        }
    }
}