using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using UI.Desktop.UFart.FakeData;

namespace UFart.Desktop.UI
{
    public partial class FormMain : Form
    {
        private Brush tabPageTitleBrush = new SolidBrush(Color.Blue);

        public FormMain()
        {
            InitializeComponent();
            InitPageStatTotal();
        }

        private void tabControl1_DrawItem(object sender, DrawItemEventArgs e)
        {
            var g = e.Graphics;
            Font font;

            var tabPage = tabControlMainScreen.TabPages[e.Index];
            var tabBounds = tabControlMainScreen.GetTabRect(e.Index);

            if (e.State == DrawItemState.Selected)
            {
                font = new Font(e.Font, FontStyle.Bold);
            }
            else
            {
                font = new Font(e.Font, FontStyle.Regular);
            }

            var stringFlags = new StringFormat();
            stringFlags.Alignment = StringAlignment.Near;
            stringFlags.LineAlignment = StringAlignment.Center;
            g.DrawString(tabPage.Text, font, tabPageTitleBrush, tabBounds, new StringFormat(stringFlags));
        }

        private void tabControlMainScreen_Selected(object sender, TabControlEventArgs e)
        {
            if (e.TabPage.Equals(tabPageStatTotal))
            {
                InitPageStatTotal();
            }
            if (e.TabPage.Equals(tabPageStatEveryDay))
            {
                InitPageStatEveryDay();
            }

        }

        private void InitPageStatEveryDay()
        {
            cbStatEveryDaySite.DataSource = new FakeSitesList(); ;
            cbStatEveryDaySite.DisplayMember = "Name";
            cbStatEveryDaySite.ValueMember = "ID";

            cbStatEveryDayPerson.DataSource = new FakePersonsList(); ;
            cbStatEveryDayPerson.DisplayMember = "Name";
            cbStatEveryDayPerson.ValueMember = "ID";
        }

        private void InitPageStatTotal()
        {
            var sites = new FakeSitesList();
            cbStatTotalSite.DataSource = sites;
            cbStatTotalSite.DisplayMember = "Name";
            cbStatTotalSite.ValueMember = "ID";
        }
    }

}
