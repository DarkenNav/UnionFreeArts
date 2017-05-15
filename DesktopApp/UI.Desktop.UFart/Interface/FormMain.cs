using AutoMapper;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using UFart.Desktop.DataAccess.FakeData;
using UFart.Desktop.DataAccess.Repositories;
using UFart.Desktop.DataAccess.Repositories.FakeDataRepositories;
using UFart.Desktop.Domain.Entity;
using UFart.Desktop.UI.DTO;
using UI.Desktop.UFart.Implimentation;
using UI.Desktop.UFart.Interface;

namespace UFart.Desktop.UI.Interface
{
    public partial class FormMain : Form
    {
        private FormEveryDay formEveryDay;
        private FormTotal formTotal;

        public FormMain()
        {
            InitializeComponent();

        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnTotalStat_Click(object sender, EventArgs e)
        {
            if (formTotal == null)
            {
                formTotal = new FormTotal();
                formTotal.FormClosed += FormTotal_FormClosed;
            }
            formTotal.Show();

        }

        private void FormTotal_FormClosed(object sender, FormClosedEventArgs e)
        {
            formTotal.Dispose();
            formTotal = null;
        }

        private void btnEveryDayStat_Click(object sender, EventArgs e)
        {
            if (formEveryDay == null)
            {
                formEveryDay = new FormEveryDay();
                formEveryDay.FormClosed += FormEveryDay_FormClosed;
            }
            formEveryDay.Show();
        }


        private void FormEveryDay_FormClosed(object sender, FormClosedEventArgs e)
        {
            formEveryDay.Dispose();
            formEveryDay = null;
        }
    }
}
