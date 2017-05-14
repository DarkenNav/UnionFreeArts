using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.Repositories;
using UFart.Desktop.DataAccess.Repositories.WebApi;

namespace UFart.WebApi.Test
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Запрос на прямую");

            var address = @"http://localhost:8080/";

            var client = new WebClient();
            client.Encoding = Encoding.UTF8;

            var reply = client.DownloadString($"{address}site/");

            Console.WriteLine(reply);
            Console.ReadKey();

            Console.WriteLine("Добавляем сайт");
            var data1 = JsonConvert.SerializeObject(new
            {
                name = "mail.ru"
            });

            reply = client.UploadString($"{address}site/", "POST", data1);

            Console.ReadKey();

            var data = JsonConvert.SerializeObject(new {
                personId = 1,
                siteId = 1,
                startDate = DateTime.MinValue.Ticks,
                finishDate = DateTime.Now.Ticks
            });

            client.Headers[HttpRequestHeader.Accept] = "application/json";
            client.Headers[HttpRequestHeader.ContentType] = "application/json";

            var reply2 = client.DownloadString("http://localhost:8080/stat/daily?personId=1&siteId=1&startDate=0&finishDate=636303822902327999");

            reply2 = client.DownloadString($"{address}/stat/daily?{data}");

            //reply2 = client.UploadString($"{address}stat/daily/", "POST", data);

            Console.ReadKey();
            //Console.WriteLine("Запрос, используя репозиторий");

            //var repo = new DataRepository(new WebApiRepository());

            //Console.WriteLine("Все Person");
            //var persons = repo.Persons.GetAll();
            //foreach (var p in persons)
            //{
            //    Console.WriteLine($"id:{p.ID}, name:{p.Name}");
            //}

            //Console.WriteLine("Person с ID = 1");
            //var person = repo.Persons.Get(1);
            //Console.WriteLine($"id:{person.ID}, name:{person.Name}");

            //Console.ReadKey();

            Console.WriteLine("Ежедневная статистика");


            Console.ReadKey();
        }
    }
}
