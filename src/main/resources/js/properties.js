export const Const = {
    AvailableWarehouseEntryStatus: "AVAILABLE",
    EmailRegExp: /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/,
    DefaultLocale: "en",
    AvailableCatalogSortings: [{ key: "name", value: "Name" }, { key: "price", value: "Price" }],
    HeadUrl: {
        ru: "localhost:9000",
        en: "localhost:9000"
    }
};

export const Links = {
    Catalog: {
        name: 'catalog',
        params: {
            page: 1
        }
    }
}

export const Modals = {
    CatalogLogin: {
        header: "catalog.cart.popup.authorization.header",
        body: "catalog.cart.popup.authorization.body"
    }
}

export const Validate = (name, value) => Const[name] === value
