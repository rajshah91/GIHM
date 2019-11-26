ALTER TABLE student ADD CONSTRAINT fk_stud_batch FOREIGN KEY (batch_id) REFERENCES batch (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE student ADD CONSTRAINT uk_stud_email UNIQUE (email_id);
ALTER TABLE course_subject ADD CONSTRAINT uk_cs_bcss UNIQUE (course_id,batch_id,semester_id,subject_id);

ALTER TABLE student DROP FOREIGN KEY fk_stud_semester;
ALTER TABLE student DROP KEY `uk_stud_email`;