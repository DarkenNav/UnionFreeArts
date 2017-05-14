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
using UFart.Desktop.Domain.Entity;
using UFart.Desktop.UI.DTO;
using UI.Desktop.UFart.Implimentation;

namespace UI.Desktop.UFart.Interface
{
    public partial class FormEveryDay : Form
    {
        public FormEveryDay()
        {
            InitializeComponent();
            InitPageStatEveryDay();
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void InitPageStatEveryDay()
        {

            var repo = AppWrapper.Data;

            cbStatEveryDaySite.DataSource = Mapper.Map<IEnumerable<Site>, List<SiteDTO>>(repo.Sites.GetAll());
            cbStatEveryDaySite.DisplayMember = "Name";

            cbStatEveryDayPerson.DataSource = Mapper.Map<IEnumerable<Person>, List<PersonDTO>>(repo.Persons.GetAll());
            cbStatEveryDayPerson.DisplayMember = "Name";
        }

        private void UpdateStatEveryDayDate()
        {
            var selectedSite = (SiteDTO)cbStatEveryDaySite.SelectedValue;
            var selectedPerson = (PersonDTO)cbStatEveryDayPerson.SelectedValue;

            var stats = AppWrapper.Data.PersonPagesRank.GetBy(
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

        private void btnStatEveryDayDateApply_Click(object sender, EventArgs e)
        {
            UpdateStatEveryDayDate();
        }
    }
}
