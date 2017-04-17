namespace UFart.Desktop.UI
{
    partial class FormMain
    {
        /// <summary>
        /// Обязательная переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Требуемый метод для поддержки конструктора — не изменяйте 
        /// содержимое этого метода с помощью редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            this.tabControlMainScreen = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.labelStatTotalSite = new System.Windows.Forms.Label();
            this.cbStatTotalSite = new System.Windows.Forms.ComboBox();
            this.btnStatTotalApply = new System.Windows.Forms.Button();
            this.listViewStatTotal = new System.Windows.Forms.ListView();
            this.columnName = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnReferenceCount = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.listViewStatEveryDay = new System.Windows.Forms.ListView();
            this.columnHeader1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.btnStatEveryDayDateApply = new System.Windows.Forms.Button();
            this.cbStatEveryDaySite = new System.Windows.Forms.ComboBox();
            this.labelStatEveryDaySite = new System.Windows.Forms.Label();
            this.cbStatEveryDayPerson = new System.Windows.Forms.ComboBox();
            this.labelStatEveryDayPerson = new System.Windows.Forms.Label();
            this.labelStatEveryDayDateFrom = new System.Windows.Forms.Label();
            this.dateStatEveryDayDateFrom = new System.Windows.Forms.DateTimePicker();
            this.labelStatEveryDayDateTo = new System.Windows.Forms.Label();
            this.dateStatEveryDayDateTo = new System.Windows.Forms.DateTimePicker();
            this.labelStatEveryDayDateSum = new System.Windows.Forms.Label();
            this.labelStatEveryDayDateSumValue = new System.Windows.Forms.Label();
            this.tabControlMainScreen.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControlMainScreen
            // 
            this.tabControlMainScreen.Alignment = System.Windows.Forms.TabAlignment.Left;
            this.tabControlMainScreen.Controls.Add(this.tabPage1);
            this.tabControlMainScreen.Controls.Add(this.tabPage2);
            this.tabControlMainScreen.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControlMainScreen.DrawMode = System.Windows.Forms.TabDrawMode.OwnerDrawFixed;
            this.tabControlMainScreen.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tabControlMainScreen.ItemSize = new System.Drawing.Size(30, 200);
            this.tabControlMainScreen.Location = new System.Drawing.Point(0, 0);
            this.tabControlMainScreen.Multiline = true;
            this.tabControlMainScreen.Name = "tabControlMainScreen";
            this.tabControlMainScreen.SelectedIndex = 0;
            this.tabControlMainScreen.Size = new System.Drawing.Size(784, 562);
            this.tabControlMainScreen.SizeMode = System.Windows.Forms.TabSizeMode.Fixed;
            this.tabControlMainScreen.TabIndex = 0;
            this.tabControlMainScreen.DrawItem += new System.Windows.Forms.DrawItemEventHandler(this.tabControl1_DrawItem);
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.listViewStatTotal);
            this.tabPage1.Controls.Add(this.btnStatTotalApply);
            this.tabPage1.Controls.Add(this.cbStatTotalSite);
            this.tabPage1.Controls.Add(this.labelStatTotalSite);
            this.tabPage1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tabPage1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.tabPage1.Location = new System.Drawing.Point(204, 4);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(576, 554);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Общая статистика";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.labelStatEveryDayDateSumValue);
            this.tabPage2.Controls.Add(this.labelStatEveryDayDateSum);
            this.tabPage2.Controls.Add(this.dateStatEveryDayDateTo);
            this.tabPage2.Controls.Add(this.labelStatEveryDayDateTo);
            this.tabPage2.Controls.Add(this.dateStatEveryDayDateFrom);
            this.tabPage2.Controls.Add(this.labelStatEveryDayDateFrom);
            this.tabPage2.Controls.Add(this.cbStatEveryDayPerson);
            this.tabPage2.Controls.Add(this.labelStatEveryDayPerson);
            this.tabPage2.Controls.Add(this.listViewStatEveryDay);
            this.tabPage2.Controls.Add(this.btnStatEveryDayDateApply);
            this.tabPage2.Controls.Add(this.cbStatEveryDaySite);
            this.tabPage2.Controls.Add(this.labelStatEveryDaySite);
            this.tabPage2.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tabPage2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.tabPage2.Location = new System.Drawing.Point(204, 4);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(576, 554);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Ежедневная статистика";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // labelStatTotalSite
            // 
            this.labelStatTotalSite.AutoSize = true;
            this.labelStatTotalSite.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatTotalSite.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatTotalSite.Location = new System.Drawing.Point(25, 26);
            this.labelStatTotalSite.Name = "labelStatTotalSite";
            this.labelStatTotalSite.Size = new System.Drawing.Size(51, 20);
            this.labelStatTotalSite.TabIndex = 0;
            this.labelStatTotalSite.Text = "Сайт:";
            // 
            // cbStatTotalSite
            // 
            this.cbStatTotalSite.FormattingEnabled = true;
            this.cbStatTotalSite.Location = new System.Drawing.Point(99, 23);
            this.cbStatTotalSite.Name = "cbStatTotalSite";
            this.cbStatTotalSite.Size = new System.Drawing.Size(190, 28);
            this.cbStatTotalSite.TabIndex = 1;
            // 
            // btnStatTotalApply
            // 
            this.btnStatTotalApply.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnStatTotalApply.Location = new System.Drawing.Point(307, 22);
            this.btnStatTotalApply.Name = "btnStatTotalApply";
            this.btnStatTotalApply.Size = new System.Drawing.Size(112, 29);
            this.btnStatTotalApply.TabIndex = 2;
            this.btnStatTotalApply.Text = "Применить";
            this.btnStatTotalApply.UseVisualStyleBackColor = true;
            // 
            // listViewStatTotal
            // 
            this.listViewStatTotal.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnName,
            this.columnReferenceCount});
            this.listViewStatTotal.FullRowSelect = true;
            this.listViewStatTotal.GridLines = true;
            this.listViewStatTotal.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.listViewStatTotal.Location = new System.Drawing.Point(29, 78);
            this.listViewStatTotal.MultiSelect = false;
            this.listViewStatTotal.Name = "listViewStatTotal";
            this.listViewStatTotal.Size = new System.Drawing.Size(425, 432);
            this.listViewStatTotal.TabIndex = 3;
            this.listViewStatTotal.TileSize = new System.Drawing.Size(100, 100);
            this.listViewStatTotal.UseCompatibleStateImageBehavior = false;
            this.listViewStatTotal.View = System.Windows.Forms.View.Details;
            // 
            // columnName
            // 
            this.columnName.Text = "Имя";
            this.columnName.Width = 200;
            // 
            // columnReferenceCount
            // 
            this.columnReferenceCount.Text = "Колличество упоминаний";
            this.columnReferenceCount.Width = 250;
            // 
            // listViewStatEveryDay
            // 
            this.listViewStatEveryDay.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader2});
            this.listViewStatEveryDay.FullRowSelect = true;
            this.listViewStatEveryDay.GridLines = true;
            this.listViewStatEveryDay.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.listViewStatEveryDay.Location = new System.Drawing.Point(28, 136);
            this.listViewStatEveryDay.MultiSelect = false;
            this.listViewStatEveryDay.Name = "listViewStatEveryDay";
            this.listViewStatEveryDay.Size = new System.Drawing.Size(425, 371);
            this.listViewStatEveryDay.TabIndex = 7;
            this.listViewStatEveryDay.TileSize = new System.Drawing.Size(100, 100);
            this.listViewStatEveryDay.UseCompatibleStateImageBehavior = false;
            this.listViewStatEveryDay.View = System.Windows.Forms.View.Details;
            // 
            // columnHeader1
            // 
            this.columnHeader1.Text = "Имя";
            this.columnHeader1.Width = 200;
            // 
            // columnHeader2
            // 
            this.columnHeader2.Text = "Колличество упоминаний";
            this.columnHeader2.Width = 250;
            // 
            // btnStatEveryDayDateApply
            // 
            this.btnStatEveryDayDateApply.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnStatEveryDayDateApply.Location = new System.Drawing.Point(430, 86);
            this.btnStatEveryDayDateApply.Name = "btnStatEveryDayDateApply";
            this.btnStatEveryDayDateApply.Size = new System.Drawing.Size(112, 29);
            this.btnStatEveryDayDateApply.TabIndex = 6;
            this.btnStatEveryDayDateApply.Text = "Применить";
            this.btnStatEveryDayDateApply.UseVisualStyleBackColor = true;
            // 
            // cbStatEveryDaySite
            // 
            this.cbStatEveryDaySite.FormattingEnabled = true;
            this.cbStatEveryDaySite.Location = new System.Drawing.Point(119, 20);
            this.cbStatEveryDaySite.Name = "cbStatEveryDaySite";
            this.cbStatEveryDaySite.Size = new System.Drawing.Size(291, 28);
            this.cbStatEveryDaySite.TabIndex = 5;
            // 
            // labelStatEveryDaySite
            // 
            this.labelStatEveryDaySite.AutoSize = true;
            this.labelStatEveryDaySite.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDaySite.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDaySite.Location = new System.Drawing.Point(24, 23);
            this.labelStatEveryDaySite.Name = "labelStatEveryDaySite";
            this.labelStatEveryDaySite.Size = new System.Drawing.Size(51, 20);
            this.labelStatEveryDaySite.TabIndex = 4;
            this.labelStatEveryDaySite.Text = "Сайт:";
            // 
            // cbStatEveryDayPerson
            // 
            this.cbStatEveryDayPerson.FormattingEnabled = true;
            this.cbStatEveryDayPerson.Location = new System.Drawing.Point(119, 54);
            this.cbStatEveryDayPerson.Name = "cbStatEveryDayPerson";
            this.cbStatEveryDayPerson.Size = new System.Drawing.Size(291, 28);
            this.cbStatEveryDayPerson.TabIndex = 9;
            // 
            // labelStatEveryDayPerson
            // 
            this.labelStatEveryDayPerson.AutoSize = true;
            this.labelStatEveryDayPerson.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayPerson.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayPerson.Location = new System.Drawing.Point(24, 57);
            this.labelStatEveryDayPerson.Name = "labelStatEveryDayPerson";
            this.labelStatEveryDayPerson.Size = new System.Drawing.Size(87, 20);
            this.labelStatEveryDayPerson.TabIndex = 8;
            this.labelStatEveryDayPerson.Text = "Личность:";
            // 
            // labelStatEveryDayDateFrom
            // 
            this.labelStatEveryDayDateFrom.AutoSize = true;
            this.labelStatEveryDayDateFrom.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayDateFrom.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayDateFrom.Location = new System.Drawing.Point(24, 93);
            this.labelStatEveryDayDateFrom.Name = "labelStatEveryDayDateFrom";
            this.labelStatEveryDayDateFrom.Size = new System.Drawing.Size(84, 20);
            this.labelStatEveryDayDateFrom.TabIndex = 10;
            this.labelStatEveryDayDateFrom.Text = "Период c:";
            // 
            // dateStatEveryDayDateFrom
            // 
            this.dateStatEveryDayDateFrom.CustomFormat = "dd.mm.yyyy";
            this.dateStatEveryDayDateFrom.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dateStatEveryDayDateFrom.Location = new System.Drawing.Point(119, 88);
            this.dateStatEveryDayDateFrom.Name = "dateStatEveryDayDateFrom";
            this.dateStatEveryDayDateFrom.Size = new System.Drawing.Size(124, 26);
            this.dateStatEveryDayDateFrom.TabIndex = 11;
            // 
            // labelStatEveryDayDateTo
            // 
            this.labelStatEveryDayDateTo.AutoSize = true;
            this.labelStatEveryDayDateTo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayDateTo.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayDateTo.Location = new System.Drawing.Point(249, 93);
            this.labelStatEveryDayDateTo.Name = "labelStatEveryDayDateTo";
            this.labelStatEveryDayDateTo.Size = new System.Drawing.Size(31, 20);
            this.labelStatEveryDayDateTo.TabIndex = 12;
            this.labelStatEveryDayDateTo.Text = "по:";
            // 
            // dateStatEveryDayDateTo
            // 
            this.dateStatEveryDayDateTo.CustomFormat = "dd.mm.yyyy";
            this.dateStatEveryDayDateTo.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dateStatEveryDayDateTo.Location = new System.Drawing.Point(286, 88);
            this.dateStatEveryDayDateTo.Name = "dateStatEveryDayDateTo";
            this.dateStatEveryDayDateTo.Size = new System.Drawing.Size(124, 26);
            this.dateStatEveryDayDateTo.TabIndex = 13;
            // 
            // labelStatEveryDayDateSum
            // 
            this.labelStatEveryDayDateSum.AutoSize = true;
            this.labelStatEveryDayDateSum.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayDateSum.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayDateSum.Location = new System.Drawing.Point(27, 520);
            this.labelStatEveryDayDateSum.Name = "labelStatEveryDayDateSum";
            this.labelStatEveryDayDateSum.Size = new System.Drawing.Size(134, 20);
            this.labelStatEveryDayDateSum.TabIndex = 14;
            this.labelStatEveryDayDateSum.Text = "Всего за период";
            this.labelStatEveryDayDateSum.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // labelStatEveryDayDateSumValue
            // 
            this.labelStatEveryDayDateSumValue.AutoSize = true;
            this.labelStatEveryDayDateSumValue.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayDateSumValue.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayDateSumValue.Location = new System.Drawing.Point(435, 520);
            this.labelStatEveryDayDateSumValue.Name = "labelStatEveryDayDateSumValue";
            this.labelStatEveryDayDateSumValue.Size = new System.Drawing.Size(18, 20);
            this.labelStatEveryDayDateSumValue.TabIndex = 15;
            this.labelStatEveryDayDateSumValue.Text = "0";
            this.labelStatEveryDayDateSumValue.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // FormMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 562);
            this.Controls.Add(this.tabControlMainScreen);
            this.Name = "FormMain";
            this.Text = "UFart";
            this.tabControlMainScreen.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            this.tabPage2.ResumeLayout(false);
            this.tabPage2.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControlMainScreen;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.Button btnStatTotalApply;
        private System.Windows.Forms.ComboBox cbStatTotalSite;
        private System.Windows.Forms.Label labelStatTotalSite;
        private System.Windows.Forms.ListView listViewStatTotal;
        private System.Windows.Forms.ColumnHeader columnName;
        private System.Windows.Forms.ColumnHeader columnReferenceCount;
        private System.Windows.Forms.ListView listViewStatEveryDay;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.ColumnHeader columnHeader2;
        private System.Windows.Forms.Button btnStatEveryDayDateApply;
        private System.Windows.Forms.ComboBox cbStatEveryDaySite;
        private System.Windows.Forms.Label labelStatEveryDaySite;
        private System.Windows.Forms.ComboBox cbStatEveryDayPerson;
        private System.Windows.Forms.Label labelStatEveryDayPerson;
        private System.Windows.Forms.DateTimePicker dateStatEveryDayDateFrom;
        private System.Windows.Forms.Label labelStatEveryDayDateFrom;
        private System.Windows.Forms.DateTimePicker dateStatEveryDayDateTo;
        private System.Windows.Forms.Label labelStatEveryDayDateTo;
        private System.Windows.Forms.Label labelStatEveryDayDateSumValue;
        private System.Windows.Forms.Label labelStatEveryDayDateSum;
    }
}

