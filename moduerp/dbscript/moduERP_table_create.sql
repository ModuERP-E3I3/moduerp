CREATE TABLE employee (
    uuid VARCHAR2(255) NOT NULL,
    biz_number VARCHAR2(50) NOT NULL,
    approval_code VARCHAR2(100) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    job_id VARCHAR2(50) NULL,
    private_authority CHAR(1) NOT NULL,
    emp_no VARCHAR2(50) NOT NULL,
    emp_name VARCHAR2(50) NOT NULL,
    emp_email VARCHAR2(50) NOT NULL,
    new_emp_pwd VARCHAR2(50) NULL,
    user_phone VARCHAR2(50) NULL,
    registration_date DATE NOT NULL,
    profile_img VARCHAR2(300) NULL,
    update_date DATE NULL,
    delete_user CHAR(1) NULL,
    last_login_location VARCHAR2(255) NOT NULL,
    deleted_excuse VARCHAR2(300) NULL,
    deleted_date DATE NULL,
    hire_date DATE NULL,
    quit_date DATE NULL,
    re_num VARCHAR2(50) NULL,
    address VARCHAR2(50) NULL,
    email_changed CHAR(1) NOT NULL,
    new_emp_email VARCHAR2(50) NULL,
    remaining_leave NUMBER NULL,
    contract_start_time DATE NOT NULL,
    contract_end_time DATE NOT NULL,
    mgr_name VARCHAR2(50) NULL
);

CREATE TABLE module (
    module_id VARCHAR2(50) NOT NULL,
    departmentid VARCHAR2(250) NOT NULL,
    module_name VARCHAR2(50) NOT NULL,
    module_price NUMBER NOT NULL,
    module_desc VARCHAR2(50) NOT NULL,
    module_ver VARCHAR2(50) NOT NULL
);

CREATE TABLE order_table (
    order_id VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    order_date DATE NOT NULL,
    total_price INT NOT NULL,
    order_status VARCHAR2(50) NOT NULL,
    order_method VARCHAR2(50) NOT NULL
);


CREATE TABLE faq (
    question_seq INT NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    question_contents VARCHAR2(1000) NOT NULL,
    question_title VARCHAR2(500) NOT NULL,
    write_date DATE NOT NULL,
    view_cnt INT NOT NULL
);

CREATE TABLE notice (
    notice_seq INT NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    notice_title VARCHAR2(50) NOT NULL,
    write_date DATE NOT NULL,
    notice_body VARCHAR2(1000) NOT NULL,
    notice_imp CHAR(1) NOT NULL,
    view_cnt INT NOT NULL,
    attach_file VARCHAR2(300) NULL
);

CREATE TABLE answer (
    a_seq INT NOT NULL,
    q_seq INT NOT NULL,
    biz_number VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    a_title VARCHAR2(50) NOT NULL,
    a_contents VARCHAR2(50) NOT NULL,
    a_date DATE NOT NULL
);

CREATE TABLE question (
    q_seq INT NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    q_title VARCHAR2(60) NOT NULL,
    q_contents VARCHAR2(255) NOT NULL,
    q_status CHAR(1) NOT NULL,
    attach_file VARCHAR2(300) NULL,
    is_public CHAR(1) NOT NULL,
    private_pwd VARCHAR2(50) NOT NULL
);

CREATE TABLE messages (
    message_id VARCHAR2(50) NOT NULL,
    chat_room_id VARCHAR2(50) NOT NULL,
    sender_id VARCHAR2(50) NOT NULL,
    message_text VARCHAR2(50) NOT NULL,
    send_at TIMESTAMP NOT NULL,
    emp_name VARCHAR2(50) NOT NULL
);

CREATE TABLE car (
    car_id VARCHAR2(30) NOT NULL,
    car_model VARCHAR2(40) NOT NULL,
    ownership_status VARCHAR2(30) NOT NULL
);

CREATE TABLE car_management (
    car_id VARCHAR2(30) NOT NULL,
    uuid2 VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    payment_history NUMBER NOT NULL,
    payment_price VARCHAR2(30) NOT NULL,
    payment_date DATE NOT NULL,
    payment_place VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL
);

CREATE TABLE item (
    item_code VARCHAR2(50) NOT NULL,
    item_name VARCHAR2(100) NOT NULL,
    item_desc VARCHAR2(255) NULL,
    unit_price NUMBER NOT NULL,
    total_stock_qty NUMBER NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NULL,
    stock_in INT NULL,
    stock_out INT NULL,
    stock INT NULL,
    stock_place VARCHAR2(100) NOT NULL
);

CREATE TABLE chat_room (
    chat_room_id VARCHAR2(50) NOT NULL,
    chat_room_name VARCHAR2(100) NOT NULL,
    is_group_chat CHAR(1) NOT NULL
);

CREATE TABLE document (
    document_id VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    document_type VARCHAR2(50) NOT NULL,
    title VARCHAR2(50) NOT NULL,
    content VARCHAR2(1000) NOT NULL,
    create_date DATE NOT NULL,
    update_date DATE NULL
);

CREATE TABLE delivery (
    deliveryid VARCHAR2(50) NOT NULL,
    item_code2 VARCHAR2(50) NOT NULL,
    spec VARCHAR2(255) NOT NULL,
    receiverid VARCHAR2(255) NOT NULL,
    address VARCHAR2(255) NOT NULL,
    delivery_type VARCHAR2(255) NOT NULL,
    mgr_name VARCHAR2(255) NOT NULL,
    mgr_phone VARCHAR2(255) NOT NULL,
    key VARCHAR2(255) NOT NULL,
    order_date DATE NOT NULL
);

CREATE TABLE car_reserve (
    car_id VARCHAR2(30) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    reserve_start_date DATE NOT NULL,
    reserve_end_date DATE NOT NULL,
    use_reason VARCHAR2(255) NOT NULL
);

CREATE TABLE electronic_payment (
    approval_id VARCHAR2(50) NOT NULL,
    document_id VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    approval_status VARCHAR2(50) NULL,
    approval_method VARCHAR2(10) NOT NULL,
    approval_date DATE NOT NULL,
    approval_limit VARCHAR2(50) NOT NULL,
    approval_now VARCHAR2(50) NULL
);

CREATE TABLE purchase_orders (
    order_id VARCHAR2(50) NOT NULL,
    item_code VARCHAR2(50) NOT NULL,
    account_no VARCHAR2(10) NOT NULL,
    quantity NUMBER NOT NULL,
    supply_price DECIMAL(15,2) NOT NULL,
    vat DECIMAL(15,2) NULL,
    delivery_date DATE NULL,
    mgr_name VARCHAR2(50) NULL,
    item_photo VARCHAR2(255) NULL
);

//////////*********//////////
CREATE TABLE buy_stock_in (
    b_stock_in_id VARCHAR2(50) NOT NULL,
    item_code VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    account_no VARCHAR2(10) NOT NULL,
    bank_id VARCHAR2(50) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    order_id VARCHAR2(50) NOT NULL,  -- `DECIMAL`을 `VARCHAR2`로 수정
    b_stock_in_date DATE NOT NULL,  -- `VARCHAR2`를 `DATE`로 수정
    b_stock_in_place VARCHAR2(100) NULL,  -- 데이터 타입 수정
    b_stock_in_qty NUMBER NOT NULL,
    b_stock_in_price VARCHAR2(50) NOT NULL,
    emp_name VARCHAR2(50) NOT NULL,
    mgr_name VARCHAR2(50) NULL
);


CREATE TABLE chat_room_participants (
    chat_room_id VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    joined_at TIMESTAMP NOT NULL,
    emp_name VARCHAR2(50) NOT NULL
);

CREATE TABLE buy_stock_out (
    b_stock_out_id VARCHAR2(30) NOT NULL,
    item_code VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    account_no VARCHAR2(10) NOT NULL,
    bank_id VARCHAR2(50) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    b_stock_out_date DATE NOT NULL,
    b_stock_out_place VARCHAR2(100) NULL,
    b_stock_out_qty NUMBER NOT NULL
);

CREATE TABLE leave (
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    remaining_leave NUMBER NOT NULL,
    leave_type VARCHAR2(20) NOT NULL,
    leave_start_date DATE NOT NULL,
    leave_end_date DATE NOT NULL,
    notes VARCHAR2(100) NULL,
    emp_name VARCHAR2(50) NOT NULL,
    mgr_name VARCHAR2(50) NULL
);

CREATE TABLE work_order (
    order_number VARCHAR2(100) NOT NULL,
    item_code VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NULL,
    task_name VARCHAR2(100) NOT NULL,
    qty NUMBER NOT NULL,
    progress_status VARCHAR2(100) NOT NULL,
    worker_team VARCHAR2(100) NOT NULL,
    worker VARCHAR2(100) NOT NULL,
    work_place VARCHAR2(100) NOT NULL,
    w_director VARCHAR2(100) NOT NULL
);

CREATE TABLE production_stock_in (
    p_stock_in_id VARCHAR2(20) NOT NULL,
    item_code VARCHAR2(50) NOT NULL,
    p_stock_in_date DATE NOT NULL,
    p_stock_place VARCHAR2(100) NULL,
    p_stock_in_qty INT NOT NULL
);

CREATE TABLE bank_account_management (
    bank_id VARCHAR2(50) NOT NULL,
    bank_name VARCHAR2(50) NOT NULL,
    bank_number VARCHAR2(50) NOT NULL,
    bank_holder VARCHAR2(50) NOT NULL,
    balance INT NOT NULL,
    transaction_date DATE NOT NULL,
    transaction_type VARCHAR2(10) NOT NULL,
    transaction_price INT NOT NULL
);

CREATE TABLE production_stock_out (
    p_stock_out_id VARCHAR2(20) NOT NULL,
    item_code VARCHAR2(50) NOT NULL,
    p_stock_out_date DATE NOT NULL,
    p_stock_out_place VARCHAR2(100) NOT NULL,
    p_stock_out_qty INT NOT NULL
);

CREATE TABLE account (
    account_no VARCHAR2(10) NOT NULL,
    account_name VARCHAR2(40) NOT NULL,
    business_type VARCHAR2(30) NULL,
    items VARCHAR2(30) NULL,
    boss_name VARCHAR2(30) NOT NULL,
    credit_limit NUMBER NULL,
    business_number VARCHAR2(50) NOT NULL,
    account_address VARCHAR2(100) NOT NULL,
    account_phone VARCHAR2(30) NOT NULL,
    postal_code VARCHAR2(40) NOT NULL,
    email VARCHAR2(100) NULL,
    fax VARCHAR2(50) NULL
);

CREATE TABLE quality_control (
    inspec_code VARCHAR2(100) NOT NULL,
    order_number VARCHAR2(100) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NULL,
    inspec_type VARCHAR2(100) NOT NULL,
    progress_status VARCHAR2(100) NOT NULL,
    inspec_result VARCHAR2(100) NOT NULL,
    inspec_qty NUMBER NOT NULL
);

CREATE TABLE sales_stock_in (
    s_stock_in_id VARCHAR2(50) NOT NULL,
    item_code VARCHAR2(50) NOT NULL,
    account_no VARCHAR2(10) NOT NULL,
    bank_id VARCHAR2(50) NOT NULL,
    stock_in_date DATE NOT NULL, -- 'date'에서 'stock_in_date'로 변경
    s_stock_in_qty NUMBER NOT NULL,
    unit_price NUMBER NOT NULL,
    s_stock_in_place VARCHAR2(100) NOT NULL
);


CREATE TABLE financial_closing (
    closing_id VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    bank_id VARCHAR2(50) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_sales INT NOT NULL,
    total_expenses INT NOT NULL,
    net_profit INT NOT NULL,
    approval_status CHAR(1) NOT NULL,
    closing_date DATE NOT NULL,
    closing_type VARCHAR2(10) NOT NULL
);

CREATE TABLE sales_stock_out (
    s_stock_out_id VARCHAR2(50) NOT NULL,
    item_code VARCHAR2(50) NOT NULL,
    account_no VARCHAR2(10) NOT NULL,
    bank_id VARCHAR2(50) NOT NULL,
    s_stock_out_date DATE NULL,
    sales_date DATE NOT NULL,
    lot VARCHAR2(20) NOT NULL,  -- 데이터 길이 수정
    s_stock_out_qty NUMBER NULL,
    unit_price NUMBER NULL,
    supply_price NUMBER NULL,
    vat NUMBER NULL,
    total_price NUMBER NOT NULL,
    s_stock_out_status CHAR(1) NOT NULL,
    salse_status VARCHAR2(100) NOT NULL,
    pannint_status CHAR(1) NULL,
    description VARCHAR2(255) NULL  -- 데이터 길이 수정
);


CREATE TABLE company (
    biz_number VARCHAR2(50) NOT NULL,
    approval_code VARCHAR2(100) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    company_name VARCHAR2(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);


///**********//////////////

CREATE TABLE order_module (
    order_module_id VARCHAR2(50) NOT NULL,
    module_id VARCHAR2(50) NOT NULL,
    order_id VARCHAR2(50) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,  -- 중복된 `departmentid`를 `department_id`로 통합
    price_at_purchase NUMBER NOT NULL
);


CREATE TABLE pay (
    pay_id VARCHAR2(50) NOT NULL,
    order_id VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    pay_price NUMBER NOT NULL,
    pay_status CHAR(1) NOT NULL,
    pay_date DATE NOT NULL,
    transaction_id VARCHAR2(50) NOT NULL
);

CREATE TABLE refund (
    refund_id VARCHAR2(50) NOT NULL,
    pay_id VARCHAR2(50) NOT NULL,
    order_id VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    refund_price NUMBER NOT NULL,
    refund_status CHAR(1) NOT NULL,
    refund_reason VARCHAR2(255) NULL,
    refund_date DATE NOT NULL,
    refund_transaction_id VARCHAR2(50) NULL
);

CREATE TABLE pay_log (
    log_id VARCHAR2(50) NOT NULL,
    pay_id VARCHAR2(50) NOT NULL,
    order_id VARCHAR2(50) NOT NULL,
    refund_id VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    status VARCHAR2(20) NOT NULL,
    timestamp DATE NOT NULL,
    details VARCHAR2(20) NULL
);

CREATE TABLE attendance (
    attendance_id VARCHAR2(50) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    department_id VARCHAR2(250) NOT NULL,
    att_date DATE NOT NULL,
    clock_in_time TIMESTAMP NULL,
    clock_out_time VARCHAR2(255) NULL,
    tot_work_hrs NUMBER NULL,
    overtime NUMBER NULL,
    emp_name VARCHAR2(50) NOT NULL
);

CREATE TABLE attachment (
    file_uuid VARCHAR2(255) NOT NULL,
    uuid VARCHAR2(255) NOT NULL,
    original_name VARCHAR2(255) NOT NULL,
    edited_name VARCHAR2(255) NOT NULL,
    file_type VARCHAR2(10) NOT NULL,
    uploaded_at TIMESTAMP NOT NULL
);

CREATE TABLE department (
    department_id VARCHAR2(250) NOT NULL,
    department VARCHAR2(30) NOT NULL
);

CREATE TABLE item_attachment (
    item_attch_id VARCHAR2(100) NOT NULL,
    item_code VARCHAR2(50) NOT NULL,
    file_uuid VARCHAR2(255) NOT NULL
);

CREATE TABLE question_attachment (
    quest_attch_id VARCHAR2(255) NOT NULL,
    q_seq INT NOT NULL,
    file_uuid VARCHAR2(255) NOT NULL
);

CREATE TABLE answer_attachment (
    answ_attach_id VARCHAR2(255) NOT NULL,
    a_seq INT NOT NULL,
    file_uuid VARCHAR2(255) NOT NULL
);

CREATE TABLE faq_attachment (
    faq_attach_id VARCHAR2(255) NOT NULL,
    question_seq INT NOT NULL,
    file_uuid VARCHAR2(255) NOT NULL
);

CREATE TABLE message_attachment (
    msg_attach_id VARCHAR2(255) NOT NULL,
    message_id VARCHAR2(50) NOT NULL,
    chat_room_id VARCHAR2(50) NOT NULL,
    file_uuid VARCHAR2(255) NOT NULL
);

CREATE TABLE document_attachment (
    doc_attach_id VARCHAR2(255) NOT NULL,
    document_id VARCHAR2(50) NOT NULL,
    file_uuid VARCHAR2(255) NOT NULL
);

CREATE TABLE car_attachment (
    car_attach_id VARCHAR2(255) NOT NULL,
    car_id VARCHAR2(30) NOT NULL,
    file_uuid VARCHAR2(255) NOT NULL
);

-- Foreign Key Constraints and Primary Key Constraints
ALTER TABLE company ADD CONSTRAINT pk_company PRIMARY KEY (biz_number, approval_code, department_id);

ALTER TABLE employee ADD CONSTRAINT pk_employee_uuid PRIMARY KEY (uuid);
ALTER TABLE employee ADD CONSTRAINT uk_employee_department UNIQUE (department_id);

ALTER TABLE department ADD CONSTRAINT pk_department PRIMARY KEY (department_id);

ALTER TABLE module ADD CONSTRAINT fk_department_to_module FOREIGN KEY (departmentid) REFERENCES department (department_id);

ALTER TABLE order_table ADD CONSTRAINT fk_employee_to_order_1 FOREIGN KEY (uuid) REFERENCES employee (uuid);
ALTER TABLE order_table ADD CONSTRAINT fk_employee_to_order_2 FOREIGN KEY (department_id) REFERENCES employee (department_id);

ALTER TABLE faq ADD CONSTRAINT fk_employee_to_faq_1 FOREIGN KEY (uuid) REFERENCES employee (uuid);
ALTER TABLE faq ADD CONSTRAINT fk_employee_to_faq_2 FOREIGN KEY (department_id) REFERENCES employee (department_id);

ALTER TABLE notice ADD CONSTRAINT fk_employee_to_notice_1 FOREIGN KEY (uuid) REFERENCES employee (uuid);
ALTER TABLE notice ADD CONSTRAINT fk_employee_to_notice_2 FOREIGN KEY (department_id) REFERENCES employee (department_id);

ALTER TABLE question ADD CONSTRAINT pk_question PRIMARY KEY (q_seq);

ALTER TABLE answer ADD CONSTRAINT fk_question_to_answer FOREIGN KEY (q_seq) REFERENCES question (q_seq);

ALTER TABLE answer ADD CONSTRAINT fk_employee_to_answer_1 FOREIGN KEY (uuid) REFERENCES employee (uuid);
ALTER TABLE answer ADD CONSTRAINT fk_employee_to_answer_2 FOREIGN KEY (department_id) REFERENCES employee (department_id);

ALTER TABLE question ADD CONSTRAINT fk_employee_to_question_1 FOREIGN KEY (uuid) REFERENCES employee (uuid);
ALTER TABLE question ADD CONSTRAINT fk_employee_to_question_2 FOREIGN KEY (department_id) REFERENCES employee (department_id);

ALTER TABLE chat_room ADD CONSTRAINT pk_chat_room PRIMARY KEY (chat_room_id);

ALTER TABLE messages ADD CONSTRAINT fk_chat_room_to_messages FOREIGN KEY (chat_room_id) REFERENCES chat_room (chat_room_id);

ALTER TABLE car ADD CONSTRAINT pk_car PRIMARY KEY (car_id);

ALTER TABLE car_management ADD CONSTRAINT fk_car_to_car_management FOREIGN KEY (car_id) REFERENCES car (car_id);

ALTER TABLE car_management ADD CONSTRAINT fk_employee_to_car_management_1 FOREIGN KEY (uuid2) REFERENCES employee (uuid);
ALTER TABLE car_management ADD CONSTRAINT fk_employee_to_car_management_2 FOREIGN KEY (department_id) REFERENCES employee (department_id);

ALTER TABLE item ADD CONSTRAINT pk_item PRIMARY KEY (item_code);

ALTER TABLE document ADD CONSTRAINT pk_document PRIMARY KEY (document_id);

ALTER TABLE document ADD CONSTRAINT fk_employee_to_document_1 FOREIGN KEY (uuid) REFERENCES employee (uuid);
ALTER TABLE document ADD CONSTRAINT fk_employee_to_document_2 FOREIGN KEY (department_id) REFERENCES employee (department_id);

-- Additional Constraints can be defined in the same way for remaining tables as needed.

-- Delivery 테이블에 외래 키 추가 (Item)
alter table delivery add constraint fk_item_to_delivery foreign key (item_code2) references item (item_code);

-- Car_reserve 테이블에 외래 키 추가 (Car)
alter table car_reserve add constraint fk_car_to_car_reserve foreign key (car_id) references car (car_id);

-- Car_reserve 테이블에 외래 키 추가 (Employee)
alter table car_reserve add constraint fk_employee_to_car_reserve_1 foreign key (uuid) references employee (uuid);
alter table car_reserve add constraint fk_employee_to_car_reserve_2 foreign key (department_id) references employee (department_id);

-- Electronic_payment 테이블에 외래 키 추가 (Document)
alter table electronic_payment add constraint fk_document_to_electronic_payment foreign key (document_id) references document (document_id);

-- Electronic_payment 테이블에 외래 키 추가 (Employee)
alter table electronic_payment add constraint fk_employee_to_electronic_payment_1 foreign key (uuid) references employee (uuid);
alter table electronic_payment add constraint fk_employee_to_electronic_payment_2 foreign key (department_id) references employee (department_id);

-- Account 테이블에 기본 키 추가
alter table account add constraint pk_account primary key (account_no);

-- Purchase_orders 테이블에 외래 키 추가 (Item)
alter table purchase_orders add constraint fk_item_to_purchase_orders foreign key (item_code) references item (item_code);

-- Purchase_orders 테이블에 외래 키 추가 (Account)
alter table purchase_orders add constraint fk_account_to_purchase_orders foreign key (account_no) references account (account_no);

-- Bank_account_management 테이블에 기본 키 추가
alter table bank_account_management add constraint pk_bank_account_management primary key (bank_id);

-- Buy_stock_in 테이블에 외래 키 추가 (Item)
alter table buy_stock_in add constraint fk_item_to_buy_stock_in foreign key (item_code) references item (item_code);

-- Buy_stock_in 테이블에 외래 키 추가 (Employee)
alter table buy_stock_in add constraint fk_employee_to_buy_stock_in_1 foreign key (uuid) references employee (uuid);
alter table buy_stock_in add constraint fk_employee_to_buy_stock_in_2 foreign key (department_id) references employee (department_id);

-- Buy_stock_in 테이블에 외래 키 추가 (Account)
alter table buy_stock_in add constraint fk_account_to_buy_stock_in foreign key (account_no) references account (account_no);

-- Buy_stock_in 테이블에 외래 키 추가 (Bank_account_management)
alter table buy_stock_in add constraint fk_bank_account_management_to_buy_stock_in foreign key (bank_id) references bank_account_management (bank_id);

-- Chat_room_participants 테이블에 외래 키 추가 (Chat_room)
alter table chat_room_participants add constraint fk_chat_room_to_chat_room_participants foreign key (chat_room_id) references chat_room (chat_room_id);

-- Chat_room_participants 테이블에 외래 키 추가 (Employee)
alter table chat_room_participants add constraint fk_employee_to_chat_room_participants_1 foreign key (uuid) references employee (uuid);
alter table chat_room_participants add constraint fk_employee_to_chat_room_participants_2 foreign key (department_id) references employee (department_id);

-- Buy_stock_out 테이블에 외래 키 추가 (Item)
alter table buy_stock_out add constraint fk_item_to_buy_stock_out foreign key (item_code) references item (item_code);

-- Buy_stock_out 테이블에 외래 키 추가 (Employee)
alter table buy_stock_out add constraint fk_employee_to_buy_stock_out_1 foreign key (uuid) references employee (uuid);
alter table buy_stock_out add constraint fk_employee_to_buy_stock_out_2 foreign key (department_id) references employee (department_id);

-- Buy_stock_out 테이블에 외래 키 추가 (Account)
alter table buy_stock_out add constraint fk_account_to_buy_stock_out foreign key (account_no) references account (account_no);

-- Buy_stock_out 테이블에 외래 키 추가 (Bank_account_management)
alter table buy_stock_out add constraint fk_bank_account_management_to_buy_stock_out foreign key (bank_id) references bank_account_management (bank_id);

-- Leave 테이블에 외래 키 추가 (Employee)
alter table leave add constraint fk_employee_to_leave_1 foreign key (uuid) references employee (uuid);
alter table leave add constraint fk_employee_to_leave_2 foreign key (department_id) references employee (department_id);

-- Work_order 테이블에 외래 키 추가 (Item)
alter table work_order add constraint fk_item_to_work_order foreign key (item_code) references item (item_code);

-- Work_order 테이블에 외래 키 추가 (Employee)
alter table work_order add constraint fk_employee_to_work_order_1 foreign key (uuid) references employee (uuid);
alter table work_order add constraint fk_employee_to_work_order_2 foreign key (department_id) references employee (department_id);

-- Production_stock_in 테이블에 외래 키 추가 (Item)
alter table production_stock_in add constraint fk_item_to_production_stock_in foreign key (item_code) references item (item_code);

-- Production_stock_out 테이블에 외래 키 추가 (Item)
alter table production_stock_out add constraint fk_item_to_production_stock_out foreign key (item_code) references item (item_code);

-- Work_order 테이블에 기본 키 추가
alter table work_order add constraint pk_work_order primary key (order_number);

-- Quality_control 테이블에 외래 키 추가 (Work_order)
alter table quality_control add constraint fk_work_order_to_quality_control_1 foreign key (order_number) references work_order (order_number);


-- Sales_stock_in 테이블에 외래 키 추가 (Item)
alter table sales_stock_in add constraint fk_item_to_sales_stock_in foreign key (item_code) references item (item_code);

-- Sales_stock_in 테이블에 외래 키 추가 (Account)
alter table sales_stock_in add constraint fk_account_to_sales_stock_in foreign key (account_no) references account (account_no);

-- Sales_stock_in 테이블에 외래 키 추가 (Bank_account_management)
alter table sales_stock_in add constraint fk_bank_account_management_to_sales_stock_in foreign key (bank_id) references bank_account_management (bank_id);




-- Financial_closing 테이블에 외래 키 추가 (Employee)
alter table financial_closing add constraint fk_employee_to_financial_closing_1 foreign key (uuid) references employee (uuid);
alter table financial_closing add constraint fk_employee_to_financial_closing_2 foreign key (department_id) references employee (department_id);

-- Sales_stock_out 테이블에 외래 키 추가 (Item)
alter table sales_stock_out add constraint fk_item_to_sales_stock_out foreign key (item_code) references item (item_code);

-- Sales_stock_out 테이블에 외래 키 추가 (Account)
alter table sales_stock_out add constraint fk_account_to_sales_stock_out foreign key (account_no) references account (account_no);

-- Sales_stock_out 테이블에 외래 키 추가 (Bank_account_management)
alter table sales_stock_out add constraint fk_bank_account_management_to_sales_stock_out foreign key (bank_id) references bank_account_management (bank_id);

-- Module 테이블의 기본 키 추가
alter table module add constraint pk_module primary key (module_id);

-- Order_module 테이블에 외래 키 추가 (Module)
alter table order_module add constraint fk_module_to_order_module foreign key (module_id) references module (module_id);

-- Order 테이블에 기본 키 추가
alter table order_table add constraint pk_order primary key (order_id);

-- Order_module 테이블에 외래 키 추가 (Order의 order_id를 참조)
alter table order_module add constraint fk_order_to_order_module foreign key (order_id) references order_table (order_id);

-- Pay 테이블에 외래 키 추가 (Order)
alter table pay add constraint fk_order_to_pay foreign key (order_id) references order_table (order_id);

-- Pay 테이블에 기본 키 추가
alter table pay add constraint pk_pay primary key (pay_id);

-- Refund 테이블에 외래 키 추가 (Pay)
alter table refund add constraint fk_pay_to_refund foreign key (pay_id) references pay (pay_id);

-- Pay_log 테이블에 외래 키 추가 (Pay)
alter table pay_log add constraint fk_pay_to_pay_log foreign key (pay_id) references pay (pay_id);

-- Attendance 테이블에 외래 키 추가 (Employee)
alter table attendance add constraint fk_employee_to_attendance foreign key (uuid) references employee (uuid);

-- Attachment 테이블에 기본 키 추가
alter table attachment add constraint pk_attachment primary key (file_uuid);

-- Item_attachment 테이블에 외래 키 추가 (Item)
alter table item_attachment add constraint fk_item_to_item_attachment foreign key (item_code) references item (item_code);

-- Item_attachment 테이블에 외래 키 추가 (Attachment)
alter table item_attachment add constraint fk_attachment_to_item_attachment foreign key (file_uuid) references attachment (file_uuid);

-- Answer 테이블에 기본 키 추가
alter table answer add constraint pk_answer primary key (a_seq);

-- Question_attachment 테이블에 외래 키 추가 (Question)
alter table question_attachment add constraint fk_question_to_question_attachment foreign key (q_seq) references question (q_seq);

-- Answer_attachment 테이블에 외래 키 추가 (Answer)
alter table answer_attachment add constraint fk_answer_to_answer_attachment foreign key (a_seq) references answer (a_seq);

-- FAQ 테이블에 기본 키 추가
alter table faq add constraint pk_faq primary key (question_seq);

-- FAQ_attachment 테이블에 외래 키 추가 (FAQ)
alter table faq_attachment add constraint fk_faq_to_faq_attachment foreign key (question_seq) references faq (question_seq);

-- Messages 테이블에 기본 키 추가
alter table messages add constraint pk_messages primary key (message_id);

-- Message_attachment 테이블에 외래 키 추가 (Messages)
alter table message_attachment add constraint fk_messages_to_message_attachment foreign key (message_id) references messages (message_id);

-- Document_attachment 테이블에 외래 키 추가 (Document)
alter table document_attachment add constraint fk_document_to_document_attachment foreign key (document_id) references document (document_id);

-- Car_attachment 테이블에 외래 키 추가 (Car)
alter table car_attachment add constraint fk_car_to_car_attachment foreign key (car_id) references car (car_id);

-- Car_attachment 테이블에 외래 키 추가 (Attachment)
alter table car_attachment add constraint fk_attachment_to_car_attachment foreign key (file_uuid) references attachment (file_uuid);

commit;
