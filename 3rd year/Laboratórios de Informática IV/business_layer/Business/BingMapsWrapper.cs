using BingMapsRESTToolkit;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Configuration;

namespace Business
{
    public static class BingMapsWrapper
    {
        public static double[] ObterCoordenadas(Endereco endereco)
        {
            SimpleAddress s = new SimpleAddress()
            {
                //s.AddressLine = "R. Dr. Alberto de Macedo 437";
                //s.AdminDistrict = "Porto";
                //s.CountryRegion = "PT";
                //s.Locality = "Porto";
                //s.PostalCode = "4100-031";

                AddressLine = endereco.Rua + " " + endereco.Numero,
                AdminDistrict = "Braga",
                CountryRegion = "PT",
                Locality = endereco.Localidade,
                PostalCode = endereco.CodPostal
            };
            var r = ServiceManager.GetResponseAsync(new GeocodeRequest()
            {
                BingMapsKey = ConfigurationManager.AppSettings.Get("BingMapsKey"),
                Culture = "pt-PT",

                Address = s,
                MaxResults = 5,
                //Address. ""


                Query = "Braga"
            }).GetAwaiter().GetResult();


            double[] dest = { 0.0f, 0.0f };


            if (r != null && r.ResourceSets != null &&
                r.ResourceSets.Length > 0 &&
                r.ResourceSets[0].Resources != null &&
                r.ResourceSets[0].Resources.Length > 0)
            {
                for (var i = 0; i < r.ResourceSets[0].Resources.Length; i++)
                {
                    Location l = r.ResourceSets[0].Resources[i] as Location;


                    dest[0] = l.GeocodePoints[0].Coordinates[0];
                    dest[1] = l.GeocodePoints[0].Coordinates[1];
                    Console.WriteLine("Resultado encontrados para {0}  com Confiança {1}", l.Name, l.Confidence);
                    Console.WriteLine("Latitude {0}  Longitude {1}", dest[0], dest[1]);



                }

            }
            else
            {
                Console.WriteLine("No results found.");
            }
            return dest;
        }

        public static Direcoes ObterDirecoes(GPSVal local, GPSVal destino)
        {

            Direcoes direc = new Direcoes();
            RouteOptions opt = new RouteOptions()
            {

                // Otimização de cálculo da rota (distância, tempo, tempo evitar fechos
                // tempo com tráfego
                Optimize = RouteOptimizationType.TimeWithTraffic,
                // Modo de viagem (carro, transportes publicos, a pé)
                TravelMode = TravelModeType.Driving
            };

           

            



            SimpleWaypoint pointDeparture = new SimpleWaypoint(new Coordinate((double)local.Latitude, (double)local.Longitude));
            SimpleWaypoint pointArrival = new SimpleWaypoint(new Coordinate((double)destino.Latitude, (double)destino.Longitude));
            List<SimpleWaypoint> points = new List<SimpleWaypoint>
            {
                pointDeparture,
                pointArrival
            };
            var r = ServiceManager.GetResponseAsync(new RouteRequest()
            {
                BingMapsKey = System.Configuration.ConfigurationManager.AppSettings.Get("BingMapsKey"),
                Culture = "pt-PT",

                RouteOptions = opt,
                Waypoints = points
            }).GetAwaiter().GetResult();


            if (r != null && r.ResourceSets != null &&
               r.ResourceSets.Length > 0
               && r.ResourceSets[0].Resources != null &&
               r.ResourceSets[0].Resources.Length > 0
               )
            {
                // Primeira rota
                Route ro = r.ResourceSets[0].Resources[0] as Route;

                foreach (var item in ro.RouteLegs[0].ItineraryItems)
                {
                    StringBuilder sb = new StringBuilder();
                    sb.Append("Distância ").Append(item.TravelDistance).Append(" Km ").Append("Duração ").Append(item.TravelDuration).AppendLine();
                    sb.Append("Instrução : ").Append(item.Instruction.Text).AppendLine();
                    direc.AdicionarDirecao(sb.ToString());
                }

            }
            else
            {
                Console.WriteLine("No results found.");
            }

            return direc;
        }
    }
}