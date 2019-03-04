using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public struct GPSVal
    {
        private double _latitude;
        private double _longitude;
        private static double MAJOR_AXIS_RADIO = 6378137;

        public GPSVal(double latitude, double longitude) :this()
        {
            Longitude = longitude;
            Latitude = latitude;
        }

        public double Longitude
        {
            get { return _longitude; }
            set
            {
                _longitude = value;
            }
        }

        public double Latitude
        {
            get { return _latitude; }
            set
            {
                _latitude = value;
            }
        }

        

        public double ToRadians(double angle)
        {
            return Math.PI * angle / 180.0;
        }

        public double DistanceTo(double latitude, double longitude)
        {

            //if (latitude > 90 && latitude < -90)
            //    throw new CoordenadaInvalidaExc("Coordenada Invalida");
           // if (longitude > 180 && longitude < -180)
            //    throw new CoordenadaInvalidaExc("Coordenada Invalida");
        
            double LatB = ToRadians(latitude);
            double LngB = ToRadians(longitude);
            double LatA = ToRadians(Latitude);
            double LngA = ToRadians(Longitude);

            return (MAJOR_AXIS_RADIO
                    * Math.Acos((Math.Cos(LatA) * Math.Cos(LatB)
                            * Math.Cos(LngB - LngA) + Math.Sin(LatA)
                            * Math.Sin(LatB))));
        }

        public String ConvertLongitude(double coordinate)
        {
            coordinate = Math.Abs(coordinate);
            String cardinal;
            int deg = (int)Math.Floor(coordinate);
            float min = (float)((coordinate - deg) * 60);
            float sec = (float)((min - Math.Floor(min)) * 60);
            if (deg < 0)
                cardinal = "E";
            else if (deg > 0)
                cardinal = "W";
            else
                cardinal = "";
            
            return String.Format("{0,-3:F0}° {1,-2:F0}' {2,-8}\" {3:F0}", deg, ((int)min), sec.ToString("0.########"), cardinal);

        }

        public String ConvertLatitude(double coordinate)
        {
            coordinate = Math.Abs(coordinate);
            
            String cardinal;
            int deg = (int)Math.Floor(coordinate);
            float min = (float)((coordinate - deg) * 60);
            float sec = (float)((min - Math.Floor(min)) * 60);
            if (deg < 0)
                cardinal = "S";
            else if (deg > 0)
                cardinal = "N";
            else
                cardinal = "";
            return String.Format("{0,-3:F0}° {1,-2:F0}' {2,-8}\" {3:F0}", deg, ((int)min), sec.ToString("0.########"), cardinal);

        }



        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();


            sb.Append("Latitude : ").Append(ConvertLatitude((double)Latitude)).AppendLine();
            sb.Append("Longitude: ").Append(ConvertLongitude((double)Longitude)).AppendLine();
          
        

            return sb.ToString();
        }




    }
}