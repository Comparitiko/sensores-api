export interface SensorData {
    columns:  Column[];
    records:  Record[];
    groupKey: Column[];
}

export interface Column {
    index:        number;
    label:        string;
    dataType:     string;
    group:        boolean;
    defaultValue: DefaultValue;
}

export enum DefaultValue {
    Empty = "",
    Result = "_result",
}

export interface Record {
    table:       number;
    values:      Values;
    row:         Array<number | string>;
    value:       number;
    field:       string;
    time:        Date;
    stop:        Date;
    measurement: string;
    start:       Date;
}

export interface Values {
    result:       DefaultValue;
    table:        number;
    _start:       Date;
    _stop:        Date;
    _time:        Date;
    _value:       number;
    _field:       string;
    _measurement: string;
    location:     string;
}
