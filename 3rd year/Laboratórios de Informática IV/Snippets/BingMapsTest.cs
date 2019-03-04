using BingMapsRESTToolkit;
using System;
using System.Collections.Generic;

namespace DemoRestBing
{
    class Program
    {
        static void Main(string[] args)
        {
            SimpleAddress s = new SimpleAddress();
            s.AddressLine = "R. Dr. Alberto de Macedo 437";
            s.AdminDistrict = "Porto";
            s.CountryRegion = "PT";
            s.Locality = "Porto";
            s.PostalCode = "4100-031";

          /*  var r = ServiceManager.GetResponseAsync(new GeocodeRequest()
            {
                BingMapsKey = System.Configuration.ConfigurationManager.AppSettings.Get("BingMapsKey"),

                Address = s,
                MaxResults = 5
                //Address. ""


                //Query = "Braga"
            }).GetAwaiter().GetResult();
            */
            //Console.WriteLine("R {0}", r.ToString());
            double[] dest = { 41.16643f , -8.65549f };

/*
            if (r != null && r.ResourceSets != null &&
                r.ResourceSets.Length > 0 &&
                r.ResourceSets[0].Resources != null &&
                r.ResourceSets[0].Resources.Length > 0)
            {
                for (var i = 0; i < r.ResourceSets[0].Resources.Length; i++)
                {
                    Location l = r.ResourceSets[0].Resources[i] as Location;
                    Console.WriteLine("Nome {0}  Confiaça {1} {2}", l.Name, l.Confidence, l.GeocodePoints.Length);

                    dest[0] = l.GeocodePoints[0].Coordinates[0];
                    dest[1] = l.GeocodePoints[0].Coordinates[1];


                }
            }
            else
            {
                Console.WriteLine("No results found.");
            }*/

            RouteOptions opt = new RouteOptions();

            // Otimização de cálculo da rota (distância, tempo, tempo evitar fechos
            // tempo com tráfego
            opt.Optimize = RouteOptimizationType.TimeWithTraffic;
            // Modo de viagem (carro, transportes publicos, a pé)
            opt.TravelMode = TravelModeType.Driving;

            double localLat = 41.562901f;
            double localLong = -8.402888f;



            Console.WriteLine("Lat {0}  Long {1}", dest[0], dest[1]);

            SimpleWaypoint pointDeparture = new SimpleWaypoint(new Coordinate(localLat, localLong));
            SimpleWaypoint pointArrival = new SimpleWaypoint(new Coordinate(dest[0], dest[1]));
            List<SimpleWaypoint> points = new List<SimpleWaypoint>();
            points.Add(pointDeparture);
            points.Add(pointArrival);

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
                    Console.WriteLine("Itinerário {0} Km {1} ", item.TravelDistance, item.Instruction.Text);
                }
                    

                 

  

               
            }
            else
            {
                Console.WriteLine("No results found.");
            }

            Console.ReadLine();
        }
    }
}
