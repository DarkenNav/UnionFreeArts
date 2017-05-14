namespace UI.Desktop.UFart.Interface
{
    partial class FormEveryDay
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormEveryDay));
            this.labelStatEveryDayDateSumValue = new System.Windows.Forms.Label();
            this.labelStatEveryDayDateSum = new System.Windows.Forms.Label();
            this.dateStatEveryDayDateTo = new System.Windows.Forms.DateTimePicker();
            this.labelStatEveryDayDateTo = new System.Windows.Forms.Label();
            this.dateStatEveryDayDateFrom = new System.Windows.Forms.DateTimePicker();
            this.labelStatEveryDayDateFrom = new System.Windows.Forms.Label();
            this.cbStatEveryDayPerson = new System.Windows.Forms.ComboBox();
            this.labelStatEveryDayPerson = new System.Windows.Forms.Label();
            this.listViewStatEveryDay = new System.Windows.Forms.ListView();
            this.columnHeader1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.btnStatEveryDayDateApply = new System.Windows.Forms.Button();
            this.cbStatEveryDaySite = new System.Windows.Forms.ComboBox();
            this.labelStatEveryDaySite = new System.Windows.Forms.Label();
            this.btnExit = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // labelStatEveryDayDateSumValue
            // 
            this.labelStatEveryDayDateSumValue.AutoSize = true;
            this.labelStatEveryDayDateSumValue.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayDateSumValue.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayDateSumValue.Location = new System.Drawing.Point(554, 521);
            this.labelStatEveryDayDateSumValue.Name = "labelStatEveryDayDateSumValue";
            this.labelStatEveryDayDateSumValue.Size = new System.Drawing.Size(18, 20);
            this.labelStatEveryDayDateSumValue.TabIndex = 27;
            this.labelStatEveryDayDateSumValue.Text = "0";
            this.labelStatEveryDayDateSumValue.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // labelStatEveryDayDateSum
            // 
            this.labelStatEveryDayDateSum.AutoSize = true;
            this.labelStatEveryDayDateSum.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayDateSum.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayDateSum.Location = new System.Drawing.Point(13, 521);
            this.labelStatEveryDayDateSum.Name = "labelStatEveryDayDateSum";
            this.labelStatEveryDayDateSum.Size = new System.Drawing.Size(138, 20);
            this.labelStatEveryDayDateSum.TabIndex = 26;
            this.labelStatEveryDayDateSum.Text = "Всего за период:";
            this.labelStatEveryDayDateSum.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // dateStatEveryDayDateTo
            // 
            this.dateStatEveryDayDateTo.CustomFormat = "dd.mm.yyyy";
            this.dateStatEveryDayDateTo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.dateStatEveryDayDateTo.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dateStatEveryDayDateTo.Location = new System.Drawing.Point(275, 89);
            this.dateStatEveryDayDateTo.Name = "dateStatEveryDayDateTo";
            this.dateStatEveryDayDateTo.Size = new System.Drawing.Size(124, 26);
            this.dateStatEveryDayDateTo.TabIndex = 25;
            // 
            // labelStatEveryDayDateTo
            // 
            this.labelStatEveryDayDateTo.AutoSize = true;
            this.labelStatEveryDayDateTo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayDateTo.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayDateTo.Location = new System.Drawing.Point(238, 94);
            this.labelStatEveryDayDateTo.Name = "labelStatEveryDayDateTo";
            this.labelStatEveryDayDateTo.Size = new System.Drawing.Size(31, 20);
            this.labelStatEveryDayDateTo.TabIndex = 24;
            this.labelStatEveryDayDateTo.Text = "по:";
            // 
            // dateStatEveryDayDateFrom
            // 
            this.dateStatEveryDayDateFrom.CustomFormat = "dd.mm.yyyy";
            this.dateStatEveryDayDateFrom.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.dateStatEveryDayDateFrom.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dateStatEveryDayDateFrom.Location = new System.Drawing.Point(108, 89);
            this.dateStatEveryDayDateFrom.Name = "dateStatEveryDayDateFrom";
            this.dateStatEveryDayDateFrom.Size = new System.Drawing.Size(124, 26);
            this.dateStatEveryDayDateFrom.TabIndex = 23;
            // 
            // labelStatEveryDayDateFrom
            // 
            this.labelStatEveryDayDateFrom.AutoSize = true;
            this.labelStatEveryDayDateFrom.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayDateFrom.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayDateFrom.Location = new System.Drawing.Point(13, 94);
            this.labelStatEveryDayDateFrom.Name = "labelStatEveryDayDateFrom";
            this.labelStatEveryDayDateFrom.Size = new System.Drawing.Size(84, 20);
            this.labelStatEveryDayDateFrom.TabIndex = 22;
            this.labelStatEveryDayDateFrom.Text = "Период c:";
            // 
            // cbStatEveryDayPerson
            // 
            this.cbStatEveryDayPerson.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbStatEveryDayPerson.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.cbStatEveryDayPerson.FormattingEnabled = true;
            this.cbStatEveryDayPerson.Location = new System.Drawing.Point(108, 55);
            this.cbStatEveryDayPerson.Name = "cbStatEveryDayPerson";
            this.cbStatEveryDayPerson.Size = new System.Drawing.Size(291, 28);
            this.cbStatEveryDayPerson.TabIndex = 21;
            // 
            // labelStatEveryDayPerson
            // 
            this.labelStatEveryDayPerson.AutoSize = true;
            this.labelStatEveryDayPerson.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDayPerson.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDayPerson.Location = new System.Drawing.Point(13, 58);
            this.labelStatEveryDayPerson.Name = "labelStatEveryDayPerson";
            this.labelStatEveryDayPerson.Size = new System.Drawing.Size(87, 20);
            this.labelStatEveryDayPerson.TabIndex = 20;
            this.labelStatEveryDayPerson.Text = "Личность:";
            // 
            // listViewStatEveryDay
            // 
            this.listViewStatEveryDay.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader2});
            this.listViewStatEveryDay.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.listViewStatEveryDay.FullRowSelect = true;
            this.listViewStatEveryDay.GridLines = true;
            this.listViewStatEveryDay.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.listViewStatEveryDay.Location = new System.Drawing.Point(12, 137);
            this.listViewStatEveryDay.MultiSelect = false;
            this.listViewStatEveryDay.Name = "listViewStatEveryDay";
            this.listViewStatEveryDay.Size = new System.Drawing.Size(560, 371);
            this.listViewStatEveryDay.TabIndex = 19;
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
            this.btnStatEveryDayDateApply.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnStatEveryDayDateApply.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnStatEveryDayDateApply.Location = new System.Drawing.Point(460, 21);
            this.btnStatEveryDayDateApply.Name = "btnStatEveryDayDateApply";
            this.btnStatEveryDayDateApply.Size = new System.Drawing.Size(112, 29);
            this.btnStatEveryDayDateApply.TabIndex = 18;
            this.btnStatEveryDayDateApply.Text = "Применить";
            this.btnStatEveryDayDateApply.UseVisualStyleBackColor = true;
            this.btnStatEveryDayDateApply.Click += new System.EventHandler(this.btnStatEveryDayDateApply_Click);
            // 
            // cbStatEveryDaySite
            // 
            this.cbStatEveryDaySite.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbStatEveryDaySite.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.cbStatEveryDaySite.FormattingEnabled = true;
            this.cbStatEveryDaySite.Location = new System.Drawing.Point(108, 21);
            this.cbStatEveryDaySite.Name = "cbStatEveryDaySite";
            this.cbStatEveryDaySite.Size = new System.Drawing.Size(291, 28);
            this.cbStatEveryDaySite.TabIndex = 17;
            // 
            // labelStatEveryDaySite
            // 
            this.labelStatEveryDaySite.AutoSize = true;
            this.labelStatEveryDaySite.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatEveryDaySite.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatEveryDaySite.Location = new System.Drawing.Point(13, 24);
            this.labelStatEveryDaySite.Name = "labelStatEveryDaySite";
            this.labelStatEveryDaySite.Size = new System.Drawing.Size(51, 20);
            this.labelStatEveryDaySite.TabIndex = 16;
            this.labelStatEveryDaySite.Text = "Сайт:";
            // 
            // btnExit
            // 
            this.btnExit.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnExit.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnExit.Location = new System.Drawing.Point(460, 56);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(112, 29);
            this.btnExit.TabIndex = 28;
            this.btnExit.Text = "Закрыть";
            this.btnExit.UseVisualStyleBackColor = true;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // FormEveryDay
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(584, 562);
            this.Controls.Add(this.btnExit);
            this.Controls.Add(this.labelStatEveryDayDateSumValue);
            this.Controls.Add(this.labelStatEveryDayDateSum);
            this.Controls.Add(this.dateStatEveryDayDateTo);
            this.Controls.Add(this.labelStatEveryDayDateTo);
            this.Controls.Add(this.dateStatEveryDayDateFrom);
            this.Controls.Add(this.labelStatEveryDayDateFrom);
            this.Controls.Add(this.cbStatEveryDayPerson);
            this.Controls.Add(this.labelStatEveryDayPerson);
            this.Controls.Add(this.listViewStatEveryDay);
            this.Controls.Add(this.btnStatEveryDayDateApply);
            this.Controls.Add(this.cbStatEveryDaySite);
            this.Controls.Add(this.labelStatEveryDaySite);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FormEveryDay";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Ежедневная статистика";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelStatEveryDayDateSumValue;
        private System.Windows.Forms.Label labelStatEveryDayDateSum;
        private System.Windows.Forms.DateTimePicker dateStatEveryDayDateTo;
        private System.Windows.Forms.Label labelStatEveryDayDateTo;
        private System.Windows.Forms.DateTimePicker dateStatEveryDayDateFrom;
        private System.Windows.Forms.Label labelStatEveryDayDateFrom;
        private System.Windows.Forms.ComboBox cbStatEveryDayPerson;
        private System.Windows.Forms.Label labelStatEveryDayPerson;
        private System.Windows.Forms.ListView listViewStatEveryDay;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.ColumnHeader columnHeader2;
        private System.Windows.Forms.Button btnStatEveryDayDateApply;
        private System.Windows.Forms.ComboBox cbStatEveryDaySite;
        private System.Windows.Forms.Label labelStatEveryDaySite;
        private System.Windows.Forms.Button btnExit;
    }
}