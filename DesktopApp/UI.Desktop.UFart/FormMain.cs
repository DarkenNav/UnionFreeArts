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
using UFart.Desktop.UI.DTO;
using UFart.Desktop.UI.FakeData;

namespace UFart.Desktop.UI
{
    public partial class FormMain : Form
    {
        private Brush tabPageTitleBrush = new SolidBrush(Color.Blue);

        private FakeDataBase fakeData;

        public FormMain()
        {
            InitializeComponent();

            fakeData = new FakeDataBase();

            InitPageStatTotal();

            UpdatelistViewStatTotal();
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
            cbStatEveryDaySite.DataSource = fakeData.Sites;
            cbStatEveryDaySite.DisplayMember = "Name";

            cbStatEveryDayPerson.DataSource = fakeData.Persons;
            cbStatEveryDayPerson.DisplayMember = "Name";
        }

        private void InitPageStatTotal()
        {
            cbStatTotalSite.DataSource = fakeData.Sites;
            cbStatTotalSite.DisplayMember = "Name";
        }

        private void btnStatTotalApply_Click(object sender, EventArgs e)
        {
            UpdatelistViewStatTotal();

        }

        private void UpdatelistViewStatTotal()
        {
            var selectedSite = (SiteDTO)cbStatTotalSite.SelectedValue;
            var stats = fakeData.Ranks.FindAll(r => 
                r.Page.SiteID == selectedSite.ID
                && r.Page.LastScanDate != null
            );

            listViewStatTotal.Items.Clear();
            foreach (var person in fakeData.Persons)
            {
                listViewStatTotal.Items.Add(new ListViewItem(new string[] {
                    person.Name,
                    stats.Where(s => s.PersonID == person.ID).Sum(s => s.Rank).ToString()
                }));
            }
        }

        private void btnStatEveryDayDateApply_Click(object sender, EventArgs e)
        {
            UpdateStatEveryDayDate();
        }

        private void UpdateStatEveryDayDate()
        {
            var selectedSite = (SiteDTO)cbStatEveryDaySite.SelectedValue;
            var selectedPerson = (PersonDTO)cbStatEveryDayPerson.SelectedValue;

            var stats = fakeData.Ranks.FindAll(r =>
                (dateStatEveryDayDateFrom.Value.Date.CompareTo((r.Page.LastScanDate ?? DateTime.MinValue).Date) <= 0
                    && dateStatEveryDayDateTo.Value.Date.CompareTo((r.Page.LastScanDate ?? DateTime.MinValue).Date) >= 0)
                && r.Page.SiteID == selectedSite.ID
                && r.PersonID == selectedPerson.ID
            );

            listViewStatEveryDay.Items.Clear();
            foreach (var stat in stats)
            {
                listViewStatEveryDay.Items.Add(new ListViewItem(new string[] {
                    stat.Page.LastScanDate?.ToString("dd.mm.yyyy"),
                    stat.Rank.ToString()
                }));
            }

            labelStatEveryDayDateSumValue.Text = stats.Sum(s => s.Rank).ToString();
        }
    }

}
