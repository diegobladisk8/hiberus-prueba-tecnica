-- Tabla de órdenes de pago
CREATE TABLE IF NOT EXISTS public.payment_order (
    id VARCHAR(50) PRIMARY KEY,
    external_reference VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    debtor_account VARCHAR(34) NOT NULL,
    creditor_account VARCHAR(34) NOT NULL,
    amount DECIMAL(19, 4) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    remittance_information VARCHAR(255),
    requested_execution_date TIMESTAMP,
    creation_date TIMESTAMP NOT NULL,
    last_update_date TIMESTAMP NOT NULL
);

-- Tabla de estados de órdenes de pago
CREATE TABLE IF NOT EXISTS public.payment_order_status (
    id VARCHAR(50) PRIMARY KEY,
    payment_order_id VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
    last_update_date TIMESTAMP NOT NULL,
    FOREIGN KEY (payment_order_id) REFERENCES payment_order(id)
);

-- Índices para mejorar el rendimiento
CREATE INDEX IF NOT EXISTS idx_payment_order_external_reference ON payment_order(external_reference);
CREATE INDEX IF NOT EXISTS idx_payment_order_status ON payment_order(status);
CREATE INDEX IF NOT EXISTS idx_payment_order_execution_date ON payment_order(requested_execution_date);
CREATE INDEX IF NOT EXISTS idx_payment_order_status_payment_order_id ON payment_order_status(payment_order_id);