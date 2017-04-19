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
using UFart.Desktop.Domain.Entity;
using UFart.Desktop.UI.DTO;

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
            using (var repo = new DataRepository(new FakeDataBase()))
            {
                cbStatEveryDaySite.DataSource = Mapper.Map<IEnumerable<Site>, List<SiteDTO>>(repo.Sites.GetAll());
                cbStatEveryDaySite.DisplayMember = "Name";

                cbStatEveryDayPerson.DataSource = Mapper.Map<IEnumerable<Person>, List<PersonDTO>>(repo.Persons.GetAll());
                cbStatEveryDayPerson.DisplayMember = "Name";
            }
        }

        private void InitPageStatTotal()
        {
            using (var repo = new DataRepository(new FakeDataBase()))
            {
                cbStatTotalSite.DataSource = Mapper.Map<IEnumerable<Site>, List<SiteDTO>>(repo.Sites.GetAll());
            }
            cbStatTotalSite.DisplayMember = "Name";

            UpdatelistViewStatTotal();
        }

        private void btnStatTotalApply_Click(object sender, EventArgs e)
        {
            UpdatelistViewStatTotal();

        }

        private void UpdatelistViewStatTotal()
        {
            var selectedSite = (SiteDTO)cbStatTotalSite.SelectedValue;
            using (var repo = new DataRepository(new FakeDataBase()))
            {
                var stats = repo.PersonPagesRank.GetBySite(selectedSite.ID);
                var persons = repo.Persons.GetAll();

                listViewStatTotal.Items.Clear();
                foreach (var person in persons)
                    {
                        listViewStatTotal.Items.Add(new ListViewItem(new string[] {
                        person.Name,
                        stats.Where(s => s.PersonID == person.ID).Sum(s => s.Rank).ToString()
                    }));
                }
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

            using (var repo = new DataRepository(new FakeDataBase()))
            {
                var stats = repo.PersonPagesRank.GetBy(
                    idSite: selectedSite.ID,
                    idPerson: selectedPerson.ID, 
                    fromDate: dateStatEveryDayDateFrom.Value,
                    toDate: dateStatEveryDayDateTo.Value
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

}
