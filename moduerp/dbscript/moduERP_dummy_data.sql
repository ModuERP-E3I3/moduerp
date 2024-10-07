-- 1. department 테이블:
INSERT INTO department (department_id, department_name, biz_number)
VALUES ('dpt001', 'HR', '654987321');

INSERT INTO department (department_id, department_name, biz_number)
VALUES ('dpt002', 'IT', '654987321');


-- company 테이블:
INSERT INTO company (biz_number, approval_code, company_name, created_at)
VALUES ('654987321', '123456', '은영컴퍼니', SYSTIMESTAMP);

-- 2. account 테이블:
insert into account (account_no, account_name, business_type, items, boss_name, credit_limit, business_number, account_address, account_phone, postal_code, email, fax)
values
('acc001', 'account 1', 'retail', 'item1', 'john boss', 10000, 'bn12345', '123 main st', '123-456-7890', '12345', 'account1@example.com', '123-456-7899');
insert into account (account_no, account_name, business_type, items, boss_name, credit_limit, business_number, account_address, account_phone, postal_code, email, fax)
values
('acc002', 'account 2', 'wholesale', 'item2', 'jane boss', 15000, 'bn12346', '456 elm st', '987-654-3210', '67890', 'account2@example.com', '987-654-3219');
insert into account (account_no, account_name, business_type, items, boss_name, credit_limit, business_number, account_address, account_phone, postal_code, email, fax)
values
('acc003', 'account 3', 'manufacturing', 'item3', 'bob boss', 20000, 'bn12347', '789 oak st', '555-123-4567', '54321', 'account3@example.com', '555-123-4569');

-- 3. employee 테이블:
insert into employee (uuid, biz_number, approval_code, department_id, job_id, private_authority, emp_no, emp_name, emp_email, new_emp_pwd, user_phone, registration_date, profile_img, update_date, delete_user, last_login_location, deleted_excuse, deleted_date, hire_date, quit_date, re_num, address, email_changed, new_emp_email, remaining_leave, contract_start_time, contract_end_time, mgr_name)
values
('uuid1', 'bn12345', 'ac1001', 'dpt001', 'j001', 'y', 'emp001', 'john doe', 'john.doe@example.com', 'pwd123', '123-456-7890', sysdate, 'img1.jpg', null, 'n', 'new york', null, null, sysdate - 365, null, null, '123 main st', 'n', null, 5, sysdate, sysdate + 365, 'manager1');
insert into employee (uuid, biz_number, approval_code, department_id, job_id, private_authority, emp_no, emp_name, emp_email, new_emp_pwd, user_phone, registration_date, profile_img, update_date, delete_user, last_login_location, deleted_excuse, deleted_date, hire_date, quit_date, re_num, address, email_changed, new_emp_email, remaining_leave, contract_start_time, contract_end_time, mgr_name)
values
('uuid2', 'bn12346', 'ac1002', 'dpt002', 'j002', 'n', 'emp002', 'jane smith', 'jane.smith@example.com', 'pwd456', '987-654-3210', sysdate, 'img2.jpg', null, 'n', 'los angeles', null, null, sysdate - 200, null, null, '456 elm st', 'n', null, 10, sysdate, sysdate + 365, 'manager2');
insert into employee (uuid, biz_number, approval_code, department_id, job_id, private_authority, emp_no, emp_name, emp_email, new_emp_pwd, user_phone, registration_date, profile_img, update_date, delete_user, last_login_location, deleted_excuse, deleted_date, hire_date, quit_date, re_num, address, email_changed, new_emp_email, remaining_leave, contract_start_time, contract_end_time, mgr_name)
values
('uuid3', 'bn12347', 'ac1003', 'dpt003', 'j003', 'y', 'emp003', 'bob johnson', 'bob.johnson@example.com', 'pwd789', '555-123-4567', sysdate, 'img3.jpg', null, 'n', 'chicago', null, null, sysdate - 100, null, null, '789 oak st', 'n', null, 8, sysdate, sysdate + 365, 'manager3');

-- 4. chat_room 테이블:
insert into chat_room (chat_room_id, chat_room_name, is_group_chat)
values
('chat001', 'project discussion', 'y');
insert into chat_room (chat_room_id, chat_room_name, is_group_chat)
values
('chat002', 'team meeting', 'n');
insert into chat_room (chat_room_id, chat_room_name, is_group_chat)
values
('chat003', 'general chat', 'y');

-- 5. question 테이블:
insert into question (q_seq, uuid, department_id, q_title, q_contents, q_status, attach_file, is_public, private_pwd)
values
(1, 'uuid1', 'dpt001', 'question 1', 'this is question 1.', 'y', null, 'y', 'pwd123');
insert into question (q_seq, uuid, department_id, q_title, q_contents, q_status, attach_file, is_public, private_pwd)
values
(2, 'uuid2', 'dpt002', 'question 2', 'this is question 2.', 'y', null, 'y', 'pwd456');
insert into question (q_seq, uuid, department_id, q_title, q_contents, q_status, attach_file, is_public, private_pwd)
values
(3, 'uuid3', 'dpt003', 'question 3', 'this is question 3.', 'n', null, 'n', 'pwd789');

-- 6. car 테이블:
insert into car (car_id, car_model, ownership_status)
values
('car001', 'toyota corolla', 'owned');
insert into car (car_id, car_model, ownership_status)
values
('car002', 'honda civic', 'leased');
insert into car (car_id, car_model, ownership_status)
values
('car003', 'ford focus', 'rented');

-- 7. item 테이블:
INSERT ALL
  INTO item (item_code, item_name, item_desc, unit_price, total_stock_qty, created_at, updated_at, stock_in, stock_out, stock, stock_place)
  VALUES ('item001', 'item 1', 'description 1', 100, 50, sysdate, null, 10, 5, 55, 'warehouse 1')
  INTO item (item_code, item_name, item_desc, unit_price, total_stock_qty, created_at, updated_at, stock_in, stock_out, stock, stock_place)
  VALUES ('item002', 'item 2', 'description 2', 200, 75, sysdate, null, 15, 10, 80, 'warehouse 2')
  INTO item (item_code, item_name, item_desc, unit_price, total_stock_qty, created_at, updated_at, stock_in, stock_out, stock, stock_place)
  VALUES ('item003', 'item 3', 'description 3', 300, 100, sysdate, null, 20, 15, 105, 'warehouse 3')
SELECT 1 FROM DUAL;

-- 8. attachment 테이블:
INSERT ALL
  INTO attachment (file_uuid, uuid, original_name, edited_name, file_type, uploaded_at)
  VALUES ('file001', 'uuid1', 'doc1.pdf', 'doc1_edited.pdf', 'pdf', systimestamp)
  INTO attachment (file_uuid, uuid, original_name, edited_name, file_type, uploaded_at)
  VALUES ('file002', 'uuid2', 'img1.jpg', 'img1_edited.jpg', 'jpg', systimestamp)
  INTO attachment (file_uuid, uuid, original_name, edited_name, file_type, uploaded_at)
  VALUES ('file003', 'uuid3', 'file1.docx', 'file1_edited.docx', 'docx', systimestamp)
SELECT 1 FROM DUAL;

-- 9. document 테이블:
INSERT ALL
  INTO document (document_id, uuid, department_id, document_type, title, content, create_date, update_date)
  VALUES ('doc001', 'uuid1', 'dpt001', 'pdf', 'project plan', 'content of document 1', sysdate, null)
  INTO document (document_id, uuid, department_id, document_type, title, content, create_date, update_date)
  VALUES ('doc002', 'uuid2', 'dpt002', 'doc', 'meeting notes', 'content of document 2', sysdate, null)
  INTO document (document_id, uuid, department_id, document_type, title, content, create_date, update_date)
  VALUES ('doc003', 'uuid3', 'dpt003', 'xls', 'financial report', 'content of document 3', sysdate, null)
SELECT 1 FROM DUAL;

-- 10. bank_account_management 테이블:
INSERT ALL
  INTO bank_account_management (bank_id, bank_name, bank_number, bank_holder, balance, transaction_date, transaction_type, transaction_price)
  VALUES ('bank001', 'bank 1', '123456789', 'john doe', 5000, sysdate, 'deposit', 1000)
  INTO bank_account_management (bank_id, bank_name, bank_number, bank_holder, balance, transaction_date, transaction_type, transaction_price)
  VALUES ('bank002', 'bank 2', '987654321', 'jane smith', 10000, sysdate, 'withdrawal', 2000)
  INTO bank_account_management (bank_id, bank_name, bank_number, bank_holder, balance, transaction_date, transaction_type, transaction_price)
  VALUES ('bank003', 'bank 3', '555555555', 'bob johnson', 15000, sysdate, 'deposit', 3000)
SELECT 1 FROM DUAL;

-- 11. module 테이블:
INSERT ALL
  INTO module (module_id, departmentid, module_name, module_price, module_desc, module_ver)
  VALUES ('mod001', 'dpt001', 'module 1', 1000, 'description of module 1', '1.0')
  INTO module (module_id, departmentid, module_name, module_price, module_desc, module_ver)
  VALUES ('mod002', 'dpt002', 'module 2', 1500, 'description of module 2', '1.1')
  INTO module (module_id, departmentid, module_name, module_price, module_desc, module_ver)
  VALUES ('mod003', 'dpt003', 'module 3', 2000, 'description of module 3', '1.2')
SELECT 1 FROM DUAL;

-- 12. order 테이블:
INSERT ALL
  INTO order_table (order_id, uuid, department_id, order_date, total_price, order_status, order_method)
  VALUES ('ord001', 'uuid1', 'dpt001', sysdate, 500, 'completed', 'credit card')
  INTO order_table (order_id, uuid, department_id, order_date, total_price, order_status, order_method)
  VALUES ('ord002', 'uuid2', 'dpt002', sysdate, 1000, 'pending', 'bank transfer')
  INTO order_table (order_id, uuid, department_id, order_date, total_price, order_status, order_method)
  VALUES ('ord003', 'uuid3', 'dpt003', sysdate, 1500, 'shipped', 'paypal')
SELECT 1 FROM DUAL;

-- 13. faq 테이블:
INSERT ALL
  INTO faq (question_seq, uuid, department_id, question_contents, question_title, write_date, view_cnt)
  VALUES (1, 'uuid1', 'dpt001', 'how to reset password?', 'password reset', sysdate, 10)
  INTO faq (question_seq, uuid, department_id, question_contents, question_title, write_date, view_cnt)
  VALUES (2, 'uuid2', 'dpt002', 'how to contact support?', 'contact support', sysdate, 5)
  INTO faq (question_seq, uuid, department_id, question_contents, question_title, write_date, view_cnt)
  VALUES (3, 'uuid3', 'dpt003', 'how to upgrade account?', 'account upgrade', sysdate, 20)
SELECT 1 FROM DUAL;

-- 14. notice 테이블:
INSERT ALL
  INTO notice (notice_seq, uuid, department_id, notice_title, write_date, notice_body, notice_imp, view_cnt, attach_file)
  VALUES (1, 'uuid1', 'dpt001', 'system maintenance', sysdate, 'the system will be down for maintenance on sunday.', 'y', 100, 'maintenance.pdf')
  INTO notice (notice_seq, uuid, department_id, notice_title, write_date, notice_body, notice_imp, view_cnt, attach_file)
  VALUES (2, 'uuid2', 'dpt002', 'new features released', sysdate, 'we have released new features in the app.', 'n', 50, null)
  INTO notice (notice_seq, uuid, department_id, notice_title, write_date, notice_body, notice_imp, view_cnt, attach_file)
  VALUES (3, 'uuid3', 'dpt003', 'holiday announcement', sysdate, 'our office will be closed during the holidays.', 'n', 30, null)
SELECT 1 FROM DUAL;

-- 15. answer 테이블:
INSERT ALL
  INTO answer (a_seq, q_seq, biz_number, uuid, department_id, a_title, a_contents, a_date)
  VALUES (1, 1, 'bn12345', 'uuid1', 'dpt001', 'answer 1', 'this is the answer to question 1.', sysdate)
  INTO answer (a_seq, q_seq, biz_number, uuid, department_id, a_title, a_contents, a_date)
  VALUES (2, 2, 'bn12346', 'uuid2', 'dpt002', 'answer 2', 'this is the answer to question 2.', sysdate)
  INTO answer (a_seq, q_seq, biz_number, uuid, department_id, a_title, a_contents, a_date)
  VALUES (3, 3, 'bn12347', 'uuid3', 'dpt003', 'answer 3', 'this is the answer to question 3.', sysdate)
SELECT 1 FROM DUAL;

-- 16. messages 테이블:
INSERT ALL
  INTO messages (message_id, chat_room_id, sender_id, message_text, send_at, emp_name)
  VALUES ('msg004', 'chat001', 'emp001', 'hello, how are you?', systimestamp, 'john doe')
  INTO messages (message_id, chat_room_id, sender_id, message_text, send_at, emp_name)
  VALUES ('msg005', 'chat002', 'emp002', 'can you provide an update?', systimestamp, 'jane smith')
  INTO messages (message_id, chat_room_id, sender_id, message_text, send_at, emp_name)
  VALUES ('msg006', 'chat003', 'emp003', 'meeting at 3 pm.', systimestamp, 'bob johnson')
SELECT 1 FROM DUAL;

-- 17. car_management 테이블:
INSERT ALL
  INTO car_management (car_id, uuid2, department_id, payment_history, payment_price, payment_date, payment_place, uuid)
  VALUES ('car001', 'uuid1', 'dpt001', 3, '500', sysdate, 'service center 1', 'uuid1')
  INTO car_management (car_id, uuid2, department_id, payment_history, payment_price, payment_date, payment_place, uuid)
  VALUES ('car002', 'uuid2', 'dpt002', 5, '300', sysdate, 'service center 2', 'uuid2')
  INTO car_management (car_id, uuid2, department_id, payment_history, payment_price, payment_date, payment_place, uuid)
  VALUES ('car003', 'uuid3', 'dpt003', 1, '700', sysdate, 'service center 3', 'uuid3')
SELECT 1 FROM DUAL;

-- 18. item_attachment 테이블:
INSERT ALL
  INTO item_attachment (item_attch_id, item_code, file_uuid)
  VALUES ('ia001', 'item001', 'file001')
  INTO item_attachment (item_attch_id, item_code, file_uuid)
  VALUES ('ia002', 'item002', 'file002')
  INTO item_attachment (item_attch_id, item_code, file_uuid)
  VALUES ('ia003', 'item003', 'file003')
SELECT 1 FROM DUAL;

-- 19. question_attachment 테이블:
INSERT ALL
  INTO question_attachment (quest_attch_id, q_seq, file_uuid)
  VALUES ('qa001', 1, 'file001')
  INTO question_attachment (quest_attch_id, q_seq, file_uuid)
  VALUES ('qa002', 2, 'file002')
  INTO question_attachment (quest_attch_id, q_seq, file_uuid)
  VALUES ('qa003', 3, 'file003')
SELECT 1 FROM DUAL;

-- 20. answer_attachment 테이블:
INSERT ALL
  INTO answer_attachment (answ_attach_id, a_seq, file_uuid)
  VALUES ('aa001', 1, 'file001')
  INTO answer_attachment (answ_attach_id, a_seq, file_uuid)
  VALUES ('aa002', 2, 'file002')
  INTO answer_attachment (answ_attach_id, a_seq, file_uuid)
  VALUES ('aa003', 3, 'file003')
SELECT 1 FROM DUAL;

-- 21. faq_attachment 테이블:
INSERT ALL
  INTO faq_attachment (faq_attach_id, question_seq, file_uuid)
  VALUES ('fa001', 1, 'file001')
  INTO faq_attachment (faq_attach_id, question_seq, file_uuid)
  VALUES ('fa002', 2, 'file002')
  INTO faq_attachment (faq_attach_id, question_seq, file_uuid)
  VALUES ('fa003', 3, 'file003')
SELECT 1 FROM DUAL;

-- 22. message_attachment 테이블:
INSERT ALL
  INTO message_attachment (msg_attach_id, message_id, chat_room_id, file_uuid)
  VALUES ('ma001', 'msg001', 'chat001', 'file001')
  INTO message_attachment (msg_attach_id, message_id, chat_room_id, file_uuid)
  VALUES ('ma002', 'msg002', 'chat002', 'file002')
  INTO message_attachment (msg_attach_id, message_id, chat_room_id, file_uuid)
  VALUES ('ma003', 'msg003', 'chat003', 'file003')
SELECT 1 FROM DUAL;

-- 23. document_attachment 테이블:
INSERT ALL
  INTO document_attachment (doc_attach_id, document_id, file_uuid)
  VALUES ('da001', 'doc001', 'file001')
  INTO document_attachment (doc_attach_id, document_id, file_uuid)
  VALUES ('da002', 'doc002', 'file002')
  INTO document_attachment (doc_attach_id, document_id, file_uuid)
  VALUES ('da003', 'doc003', 'file003')
SELECT 1 FROM DUAL;

-- 24. car_attachment 테이블:
INSERT ALL
  INTO car_attachment (car_attach_id, car_id, file_uuid)
  VALUES ('ca001', 'car001', 'file001')
  INTO car_attachment (car_attach_id, car_id, file_uuid)
  VALUES ('ca002', 'car002', 'file002')
  INTO car_attachment (car_attach_id, car_id, file_uuid)
  VALUES ('ca003', 'car003', 'file003')
SELECT 1 FROM DUAL;

-- 25. order_module 테이블:
INSERT ALL
  INTO order_module (order_module_id, module_id, order_id, departmentid, department_id, price_at_purchase)
  VALUES ('om001', 'mod001', 'ord001', 'dpt001', 'dpt001', 1000)
  INTO order_module (order_module_id, module_id, order_id, departmentid, department_id, price_at_purchase)
  VALUES ('om002', 'mod002', 'ord002', 'dpt002', 'dpt002', 1500)
  INTO order_module (order_module_id, module_id, order_id, departmentid, department_id, price_at_purchase)
  VALUES ('om003', 'mod003', 'ord003', 'dpt003', 'dpt003', 2000)
SELECT 1 FROM DUAL;

-- 26. pay 테이블:
INSERT ALL
  INTO pay (pay_id, order_id, uuid, department_id, pay_price, pay_status, pay_date, transaction_id)
  VALUES ('pay001', 'ord001', 'uuid1', 'dpt001', 500, 'c', sysdate, 'txn001')
  INTO pay (pay_id, order_id, uuid, department_id, pay_price, pay_status, pay_date, transaction_id)
  VALUES ('pay002', 'ord002', 'uuid2', 'dpt002', 1000, 'p', sysdate, 'txn002')
  INTO pay (pay_id, order_id, uuid, department_id, pay_price, pay_status, pay_date, transaction_id)
  VALUES ('pay003', 'ord003', 'uuid3', 'dpt003', 1500, 's', sysdate, 'txn003')
SELECT 1 FROM DUAL;

-- 27. refund 테이블:
INSERT ALL
  INTO refund (refund_id, pay_id, order_id, uuid, department_id, refund_price, refund_status, refund_reason, refund_date, refund_transaction_id)
  VALUES ('rf001', 'pay001', 'ord001', 'uuid1', 'dpt001', 250, 'a', 'damaged item', sysdate, 'rtxn001')
  INTO refund (refund_id, pay_id, order_id, uuid, department_id, refund_price, refund_status, refund_reason, refund_date, refund_transaction_id)
  VALUES ('rf002', 'pay002', 'ord002', 'uuid2', 'dpt002', 500, 'p', 'wrong item', sysdate, 'rtxn002')
  INTO refund (refund_id, pay_id, order_id, uuid, department_id, refund_price, refund_status, refund_reason, refund_date, refund_transaction_id)
  VALUES ('rf003', 'pay003', 'ord003', 'uuid3', 'dpt003', 750, 'r', 'late delivery', sysdate, 'rtxn003')
SELECT 1 FROM DUAL;


-- 28. pay_log 테이블:
INSERT ALL
  INTO pay_log (log_id, pay_id, order_id, refund_id, uuid, department_id, status, timestamp, details)
  VALUES ('log001', 'pay001', 'ord001', 'rf001', 'uuid1', 'dpt001', 'processed', sysdate, 'payment successful')
  INTO pay_log (log_id, pay_id, order_id, refund_id, uuid, department_id, status, timestamp, details)
  VALUES ('log002', 'pay002', 'ord002', 'rf002', 'uuid2', 'dpt002', 'pending', sysdate, 'awaiting refund')
  INTO pay_log (log_id, pay_id, order_id, refund_id, uuid, department_id, status, timestamp, details)
  VALUES ('log003', 'pay003', 'ord003', 'rf003', 'uuid3', 'dpt003', 'failed', sysdate, 'refund rejected')
SELECT 1 FROM DUAL;

-- 29. attendance 테이블:
INSERT ALL
  INTO attendance (attendance_id, uuid, department_id, att_date, clock_in_time, clock_out_time, tot_work_hrs, overtime, emp_name)
  VALUES ('att001', 'uuid1', 'dpt001', sysdate, to_timestamp('08:00', 'hh24:mi'), to_timestamp('17:00', 'hh24:mi'), 9, 0, 'john doe')
  INTO attendance (attendance_id, uuid, department_id, att_date, clock_in_time, clock_out_time, tot_work_hrs, overtime, emp_name)
  VALUES ('att002', 'uuid2', 'dpt002', sysdate, to_timestamp('09:00', 'hh24:mi'), to_timestamp('18:00', 'hh24:mi'), 9, 1, 'jane smith')
  INTO attendance (attendance_id, uuid, department_id, att_date, clock_in_time, clock_out_time, tot_work_hrs, overtime, emp_name)
  VALUES ('att003', 'uuid3', 'dpt003', sysdate, to_timestamp('10:00', 'hh24:mi'), to_timestamp('19:00', 'hh24:mi'), 9, 2, 'bob johnson')
SELECT 1 FROM DUAL;

-- 30. work_order 테이블:
INSERT ALL
  INTO work_order (order_number, item_code, uuid, department_id, start_date, end_date, task_name, qty, progress_status, worker_team, worker, work_place, w_director)
  VALUES ('wo001', 'item001', 'uuid1', 'dpt001', sysdate, sysdate + 7, 'task 1', 100, 'in progress', 'team a', 'john doe', 'factory 1', 'director1')
  INTO work_order (order_number, item_code, uuid, department_id, start_date, end_date, task_name, qty, progress_status, worker_team, worker, work_place, w_director)
  VALUES ('wo002', 'item002', 'uuid2', 'dpt002', sysdate, sysdate + 10, 'task 2', 150, 'completed', 'team b', 'jane smith', 'factory 2', 'director2')
  INTO work_order (order_number, item_code, uuid, department_id, start_date, end_date, task_name, qty, progress_status, worker_team, worker, work_place, w_director)
  VALUES ('wo003', 'item003', 'uuid3', 'dpt003', sysdate, sysdate + 15, 'task 3', 200, 'pending', 'team c', 'bob johnson', 'factory 3', 'director3')
SELECT 1 FROM DUAL;

-- 31. production_stock_in 테이블:
INSERT ALL
  INTO production_stock_in (p_stock_in_id, item_code, p_stock_in_date, p_stock_place, p_stock_in_qty)
  VALUES ('psi001', 'item001', sysdate, 'factory 1', 100)
  INTO production_stock_in (p_stock_in_id, item_code, p_stock_in_date, p_stock_place, p_stock_in_qty)
  VALUES ('psi002', 'item002', sysdate, 'factory 2', 150)
  INTO production_stock_in (p_stock_in_id, item_code, p_stock_in_date, p_stock_place, p_stock_in_qty)
  VALUES ('psi003', 'item003', sysdate, 'factory 3', 200)
SELECT 1 FROM DUAL;

-- 32. production_stock_out 테이블:
INSERT ALL
  INTO production_stock_out (p_stock_out_id, item_code, p_stock_out_date, p_stock_out_place, p_stock_out_qty)
  VALUES ('pso001', 'item001', sysdate, 'factory 1', 50)
  INTO production_stock_out (p_stock_out_id, item_code, p_stock_out_date, p_stock_out_place, p_stock_out_qty)
  VALUES ('pso002', 'item002', sysdate, 'factory 2', 75)
  INTO production_stock_out (p_stock_out_id, item_code, p_stock_out_date, p_stock_out_place, p_stock_out_qty)
  VALUES ('pso003', 'item003', sysdate, 'factory 3', 100)
SELECT 1 FROM DUAL;

-- 33. sales_stock_in 테이블:
INSERT ALL
  INTO sales_stock_in (s_stock_in_id, item_code, account_no, bank_id, stock_in_date, s_stock_in_qty, unit_price, s_stock_in_place)
  VALUES ('ssi001', 'item001', 'acc001', 'bank001', sysdate, 100, 1000, 'warehouse 1')
  INTO sales_stock_in (s_stock_in_id, item_code, account_no, bank_id, stock_in_date, s_stock_in_qty, unit_price, s_stock_in_place)
  VALUES ('ssi002', 'item002', 'acc002', 'bank002', sysdate, 150, 1500, 'warehouse 2')
  INTO sales_stock_in (s_stock_in_id, item_code, account_no, bank_id, stock_in_date, s_stock_in_qty, unit_price, s_stock_in_place)
  VALUES ('ssi003', 'item003', 'acc003', 'bank003', sysdate, 200, 2000, 'warehouse 3')
SELECT 1 FROM DUAL;

-- 34. sales_stock_out 테이블:
INSERT ALL
  INTO sales_stock_out (s_stock_out_id, item_code, account_no, bank_id, s_stock_out_date, sales_date, lot, s_stock_out_qty, unit_price, supply_price, vat, total_price, s_stock_out_status, salse_status, pannint_status, description)
  VALUES ('sso001', 'item001', 'acc001', 'bank001', sysdate, sysdate + 7, 'lot001', 50, 1000, 5000, 500, 5500, 'y', 'completed', 'n', 'description 1')
  INTO sales_stock_out (s_stock_out_id, item_code, account_no, bank_id, s_stock_out_date, sales_date, lot, s_stock_out_qty, unit_price, supply_price, vat, total_price, s_stock_out_status, salse_status, pannint_status, description)
  VALUES ('sso002', 'item002', 'acc002', 'bank002', sysdate, sysdate + 10, 'lot002', 75, 1500, 7500, 750, 8250, 'y', 'shipped', 'n', 'description 2')
  INTO sales_stock_out (s_stock_out_id, item_code, account_no, bank_id, s_stock_out_date, sales_date, lot, s_stock_out_qty, unit_price, supply_price, vat, total_price, s_stock_out_status, salse_status, pannint_status, description)
  VALUES ('sso003', 'item003', 'acc003', 'bank003', sysdate, sysdate + 15, 'lot003', 100, 2000, 10000, 1000, 11000, 'n', 'pending', 'y', 'description 3')
SELECT 1 FROM DUAL;

-- 35. quality_control 테이블:
INSERT ALL
  INTO quality_control (inspec_code, order_number, uuid, department_id, start_date, end_date, inspec_type, progress_status, inspec_result, inspec_qty)
  VALUES ('qc001', 'wo001', 'uuid1', 'dpt001', sysdate, sysdate + 3, 'visual', 'in progress', 'pass', 50)
  INTO quality_control (inspec_code, order_number, uuid, department_id, start_date, end_date, inspec_type, progress_status, inspec_result, inspec_qty)
  VALUES ('qc002', 'wo002', 'uuid2', 'dpt002', sysdate, sysdate + 5, 'mechanical', 'completed', 'pass', 75)
  INTO quality_control (inspec_code, order_number, uuid, department_id, start_date, end_date, inspec_type, progress_status, inspec_result, inspec_qty)
  VALUES ('qc003', 'wo003', 'uuid3', 'dpt003', sysdate, sysdate + 7, 'electrical', 'pending', 'fail', 100)
SELECT 1 FROM DUAL;

-- 36. leave 테이블:
INSERT ALL
  INTO leave (uuid, department_id, remaining_leave, leave_type, leave_start_date, leave_end_date, notes, emp_name, mgr_name)
  VALUES ('uuid1', 'dpt001', 5, 'sick', sysdate, sysdate + 3, 'flu', 'john doe', 'manager1')
  INTO leave (uuid, department_id, remaining_leave, leave_type, leave_start_date, leave_end_date, notes, emp_name, mgr_name)
  VALUES ('uuid2', 'dpt002', 10, 'vacation', sysdate + 10, sysdate + 15, 'family trip', 'jane smith', 'manager2')
  INTO leave (uuid, department_id, remaining_leave, leave_type, leave_start_date, leave_end_date, notes, emp_name, mgr_name)
  VALUES ('uuid3', 'dpt003', 8, 'personal', sysdate + 5, sysdate + 7, 'moving', 'bob johnson', 'manager3')
SELECT 1 FROM DUAL;

-- 37. purchase_orders 테이블:
INSERT ALL
  INTO purchase_orders (order_id, item_code, account_no, quantity, supply_price, vat, delivery_date, mgr_name, item_photo)
  VALUES ('po001', 'item001', 'acc001', 100, 10000, 1000, sysdate + 7, 'john manager', 'item1.jpg')
  INTO purchase_orders (order_id, item_code, account_no, quantity, supply_price, vat, delivery_date, mgr_name, item_photo)
  VALUES ('po002', 'item002', 'acc002', 150, 15000, 1500, sysdate + 10, 'jane manager', 'item2.jpg')
  INTO purchase_orders (order_id, item_code, account_no, quantity, supply_price, vat, delivery_date, mgr_name, item_photo)
  VALUES ('po003', 'item003', 'acc003', 200, 20000, 2000, sysdate + 15, 'bob manager', 'item3.jpg')
SELECT 1 FROM DUAL;

-- 38. buy_stock_in 테이블:
INSERT ALL
  INTO buy_stock_in (b_stock_in_id, item_code, uuid, account_no, bank_id, department_id, order_id, b_stock_in_date, b_stock_in_place, b_stock_in_qty, b_stock_in_price, emp_name, mgr_name)
  VALUES ('bsi001', 'item001', 'uuid1', 'acc001', 'bank001', 'dpt001', 'po001', to_date('2022-09-11', 'yyyy-mm-dd'), 'warehouse 1', 100, 1000, 'john doe', 'manager1')
  INTO buy_stock_in (b_stock_in_id, item_code, uuid, account_no, bank_id, department_id, order_id, b_stock_in_date, b_stock_in_place, b_stock_in_qty, b_stock_in_price, emp_name, mgr_name)
  VALUES ('bsi002', 'item002', 'uuid2', 'acc002', 'bank002', 'dpt002', 'po002', to_date('2022-09-11', 'yyyy-mm-dd'), 'warehouse 2', 150, 1500, 'jane smith', 'manager2')
  INTO buy_stock_in (b_stock_in_id, item_code, uuid, account_no, bank_id, department_id, order_id, b_stock_in_date, b_stock_in_place, b_stock_in_qty, b_stock_in_price, emp_name, mgr_name)
  VALUES ('bsi003', 'item003', 'uuid3', 'acc003', 'bank003', 'dpt003', 'po003', to_date('2022-09-11', 'yyyy-mm-dd'), 'warehouse 3', 200, 2000, 'bob johnson', 'manager3')
SELECT 1 FROM DUAL;

-- 39. buy_stock_out 테이블:
INSERT ALL
  INTO buy_stock_out (b_stock_out_id, item_code, uuid, account_no, bank_id, department_id, b_stock_out_date, b_stock_out_place, b_stock_out_qty)
  VALUES ('bso001', 'item001', 'uuid1', 'acc001', 'bank001', 'dpt001', sysdate, 'warehouse 1', 50)
  INTO buy_stock_out (b_stock_out_id, item_code, uuid, account_no, bank_id, department_id, b_stock_out_date, b_stock_out_place, b_stock_out_qty)
  VALUES ('bso002', 'item002', 'uuid2', 'acc002', 'bank002', 'dpt002', sysdate, 'warehouse 2', 75)
  INTO buy_stock_out (b_stock_out_id, item_code, uuid, account_no, bank_id, department_id, b_stock_out_date, b_stock_out_place, b_stock_out_qty)
  VALUES ('bso003', 'item003', 'uuid3', 'acc003', 'bank003', 'dpt003', sysdate, 'warehouse 3', 100)
SELECT 1 FROM DUAL;

-- 40. financial_closing 테이블:
INSERT ALL
  INTO financial_closing (closing_id, uuid, bank_id, department_id, start_date, end_date, total_sales, total_expenses, net_profit, approval_status, closing_date, closing_type)
  VALUES ('fc001', 'uuid1', 'bank001', 'dpt001', sysdate - 30, sysdate, 100000, 50000, 50000, 'a', sysdate, 'monthly')
  INTO financial_closing (closing_id, uuid, bank_id, department_id, start_date, end_date, total_sales, total_expenses, net_profit, approval_status, closing_date, closing_type)
  VALUES ('fc002', 'uuid2', 'bank002', 'dpt002', sysdate - 30, sysdate, 150000, 60000, 90000, 'p', sysdate, 'monthly')
  INTO financial_closing (closing_id, uuid, bank_id, department_id, start_date, end_date, total_sales, total_expenses, net_profit, approval_status, closing_date, closing_type)
  VALUES ('fc003', 'uuid3', 'bank003', 'dpt003', sysdate - 30, sysdate, 200000, 70000, 130000, 'a', sysdate, 'monthly')
SELECT 1 FROM DUAL;

-- 41. chat_room_participants 테이블:
INSERT ALL
  INTO chat_room_participants (chat_room_id, uuid, department_id, joined_at, emp_name)
  VALUES ('chat001', 'uuid1', 'dpt001', systimestamp, 'john doe')
  INTO chat_room_participants (chat_room_id, uuid, department_id, joined_at, emp_name)
  VALUES ('chat002', 'uuid2', 'dpt002', systimestamp, 'jane smith')
  INTO chat_room_participants (chat_room_id, uuid, department_id, joined_at, emp_name)
  VALUES ('chat003', 'uuid3', 'dpt003', systimestamp, 'bob johnson')
SELECT 1 FROM DUAL;

-- 42. electronic_payment 테이블:
INSERT ALL
  INTO electronic_payment (approval_id, document_id, uuid, department_id, approval_status, approval_method, approval_date, approval_limit, approval_now)
  VALUES ('apv001', 'doc001', 'uuid1', 'dpt001', 'a', 'online', sysdate, '1000', '500')
  INTO electronic_payment (approval_id, document_id, uuid, department_id, approval_status, approval_method, approval_date, approval_limit, approval_now)
  VALUES ('apv002', 'doc002', 'uuid2', 'dpt002', 'p', 'offline', sysdate, '2000', '1000')
  INTO electronic_payment (approval_id, document_id, uuid, department_id, approval_status, approval_method, approval_date, approval_limit, approval_now)
  VALUES ('apv003', 'doc003', 'uuid3', 'dpt003', 'r', 'online', sysdate, '1500', '700')
SELECT 1 FROM DUAL;

-- 43. car_reserve 테이블:
INSERT ALL
  INTO car_reserve (car_id, uuid, department_id, reserve_start_date, reserve_end_date, use_reason)
  VALUES ('car001', 'uuid1', 'dpt001', sysdate, sysdate + 7, 'business trip')
  INTO car_reserve (car_id, uuid, department_id, reserve_start_date, reserve_end_date, use_reason)
  VALUES ('car002', 'uuid2', 'dpt002', sysdate, sysdate + 3, 'client meeting')
  INTO car_reserve (car_id, uuid, department_id, reserve_start_date, reserve_end_date, use_reason)
  VALUES ('car003', 'uuid3', 'dpt003', sysdate, sysdate + 5, 'training session')
SELECT 1 FROM DUAL;

-- 44. delivery 테이블:
DESC delivery;

INSERT ALL
  INTO delivery (delivery_id, item_code2, spec, receiver_id, address, delivery_type, mgr_name, mgr_phone, key, order_date)
  VALUES ('del001', 'item001', 'spec 1', 'uuid1', '123 main st', 'express', 'john manager', '123-456-7890', 'key1', sysdate)
  INTO delivery (delivery_id, item_code2, spec, receiver_id, address, delivery_type, mgr_name, mgr_phone, key, order_date)
  VALUES ('del002', 'item002', 'spec 2', 'uuid2', '456 elm st', 'standard', 'jane manager', '987-654-3210', 'key2', sysdate)
  INTO delivery (delivery_id, item_code2, spec, receiver_id, address, delivery_type, mgr_name, mgr_phone, key, order_date)
  VALUES ('del003', 'item003', 'spec 3', 'uuid3', '789 oak st', 'overnight', 'bob manager', '555-123-4567', 'key3', sysdate)
SELECT 1 FROM DUAL;

-- 45. messages 테이블:
INSERT ALL
  INTO messages (message_id, chat_room_id, sender_id, message_text, send_at, emp_name)
  VALUES ('msg001', 'chat001', 'emp001', 'hello, how are you?', systimestamp, 'john doe')
  INTO messages (message_id, chat_room_id, sender_id, message_text, send_at, emp_name)
  VALUES ('msg002', 'chat002', 'emp002', 'can you provide an update?', systimestamp, 'jane smith')
  INTO messages (message_id, chat_room_id, sender_id, message_text, send_at, emp_name)
  VALUES ('msg003', 'chat003', 'emp003', 'meeting at 3 pm.', systimestamp, 'bob johnson')
SELECT 1 FROM DUAL;

commit;



