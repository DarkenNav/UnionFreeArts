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
using UFart.Desktop.UI.FakeDataDTO;
using UI.Desktop.UFart.Mapping;

namespace UFart.Desktop.UI
{
    public partial class FormMain : Form
    {
        private Brush tabPageTitleBrush = new SolidBrush(Color.Blue);

        private FakeDataDTOBase fakeData;

        public FormMain()
        {
            InitializeComponent();

            fakeData = new FakeDataDTOBase();

            InitPageStatTotal();
            
            //using (var repo = new DataRepository(new FakeDataBase()))
            //{
            //    var sites = repo.Sites.GetAll();
            //    var sitesDto =  Mapper.Map<IEnumerable<Site>,List<SiteDTO>>(sites);

            //    var persons = repo.Persons.GetAll();
            //    var personsDto = Mapper.Map<IEnumerable<Person>, List<PersonDTO>>(persons);
            //}
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
            using (var repo = new DataRepository(new FakeDataBase()))
            {
                cbStatTotalSite.DataSource = Mapper.Map<IEnumerable<Site>, List<SiteDTO>>(repo.Sites.GetAll());
            }
            //cbStatTotalSite.DataSource = fakeData.Sites;
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
                //var stats = fakeData.Ranks.FindAll(r => 
                //    r.Page.SiteID == selectedSite.ID
                //    && r.Page.LastScanDate != null
                //);

                listViewStatTotal.Items.Clear();
//                foreach (var person in fakeData.Persons)
                foreach (var person in persons)
                    {
                        listViewStatTotal.Items.Add(new ListViewItem(new string[] {
                        person.Name,
                        stats.Where(s => s.PersonID == person.ID).Sum(s => s.Rank).ToString()
                    }));

                    //listViewStatTotal.Items.Add(new ListViewItem(new string[] {
                    //    person.Name,
                    //    stats.Where(s => s.PersonID == person.ID).Sum(s => s.Rank).ToString()
                    //}));
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
