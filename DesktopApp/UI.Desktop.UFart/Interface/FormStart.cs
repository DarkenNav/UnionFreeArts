using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using UFart.Desktop.DataAccess.Repositories;
using UFart.Desktop.DataAccess.Repositories.FakeDataRepositories;
using UFart.Desktop.UI;
using UFart.Desktop.UI.Interface;
using UI.Desktop.UFart.Implimentation;

namespace UI.Desktop.UFart.Interface
{
    public partial class FormStart : Form
    {
        private FormMain main;

        public FormStart()
        {
            InitializeComponent();
        }

        private void btnDemoRepo_Click(object sender, EventArgs e)
        {
            AppWrapper.Data = new DataRepository(new FakeDataRepository());
            ToMainScreen();

        }

        private void btnWebApiRepo_Click(object sender, EventArgs e)
        {

            ToMainScreen();
        }

        private void ToMainScreen()
        {
            main = new FormMain();
            main.FormClosed += Main_FormClosed;
            main.Show();

            this.Hide();
        }

        private void Main_FormClosed(object sender, FormClosedEventArgs e)
        {
            this.Show();
        }

        private void FormStart_FormClosing(object sender, FormClosingEventArgs e)
        {
            AppWrapper.Data.Dispose();
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
