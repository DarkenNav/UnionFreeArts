namespace UI.Desktop.UFart.Interface
{
    partial class FormStart
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormStart));
            this.btnDemoRepo = new System.Windows.Forms.Button();
            this.btnWebApiRepo = new System.Windows.Forms.Button();
            this.btnExit = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btnDemoRepo
            // 
            this.btnDemoRepo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnDemoRepo.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnDemoRepo.Location = new System.Drawing.Point(12, 70);
            this.btnDemoRepo.Name = "btnDemoRepo";
            this.btnDemoRepo.Size = new System.Drawing.Size(210, 29);
            this.btnDemoRepo.TabIndex = 3;
            this.btnDemoRepo.Text = "Демо-версия";
            this.btnDemoRepo.UseVisualStyleBackColor = true;
            this.btnDemoRepo.Click += new System.EventHandler(this.btnDemoRepo_Click);
            // 
            // btnWebApiRepo
            // 
            this.btnWebApiRepo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnWebApiRepo.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnWebApiRepo.Location = new System.Drawing.Point(12, 124);
            this.btnWebApiRepo.Name = "btnWebApiRepo";
            this.btnWebApiRepo.Size = new System.Drawing.Size(210, 29);
            this.btnWebApiRepo.TabIndex = 4;
            this.btnWebApiRepo.Text = "Триал-версия";
            this.btnWebApiRepo.UseVisualStyleBackColor = true;
            this.btnWebApiRepo.Click += new System.EventHandler(this.btnWebApiRepo_Click);
            // 
            // btnExit
            // 
            this.btnExit.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnExit.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnExit.Location = new System.Drawing.Point(12, 221);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(210, 29);
            this.btnExit.TabIndex = 5;
            this.btnExit.Text = "Выход";
            this.btnExit.UseVisualStyleBackColor = true;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // FormStart
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(234, 262);
            this.Controls.Add(this.btnExit);
            this.Controls.Add(this.btnWebApiRepo);
            this.Controls.Add(this.btnDemoRepo);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FormStart";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Union Free Arts";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.FormStart_FormClosing);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btnDemoRepo;
        private System.Windows.Forms.Button btnWebApiRepo;
        private System.Windows.Forms.Button btnExit;
    }
}