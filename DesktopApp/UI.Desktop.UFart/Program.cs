using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using UI.Desktop.UFart.Interface;
using UI.Desktop.UFart.Mapping;

namespace UFart.Desktop.UI
{
    static class Program
    {
        /// <summary>
        /// Главная точка входа для приложения.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Mapper.Initialize(cfg => MapperConfigurate.Initialize(cfg));

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            Application.Run(new FormStart());
        }
    }
}
