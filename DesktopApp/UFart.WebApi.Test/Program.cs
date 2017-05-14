using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
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

            var address = @"http://localhost:8080/site/";
            var method = "GET";
            var data = "";

            var client = new WebClient();
            client.Encoding = Encoding.UTF8;

            var reply = client.DownloadString(address);

            //reply = client.UploadString(address, method, data);

            Console.WriteLine(reply);

            Console.ReadKey();
            Console.WriteLine("Запрос, используя репозиторий");

            var repo = new DataRepository(new WebApiRepository());

            Console.WriteLine("Все Person");
            var persons = repo.Persons.GetAll();
            foreach (var p in persons)
            {
                Console.WriteLine($"id:{p.ID}, name:{p.Name}");
            }

            Console.WriteLine("Person с ID = 1");
            var person = repo.Persons.Get(1);
            Console.WriteLine($"id:{person.ID}, name:{person.Name}");

            Console.ReadKey();
        }
    }
}
