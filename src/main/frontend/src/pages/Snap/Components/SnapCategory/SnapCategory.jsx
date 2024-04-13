import React, {useEffect, useState} from 'react';
import Filter from "widgets/Filter/Filter";
import { snap_category } from "data/category";

const SnapCategory = ({ selectedCategories, setSelectedCategories }) => {

    const categoryProps = snap_category.reduce((acc, cur) => {
        acc[cur.group] = cur.items;
        return acc;
    }, {});


    return (
        <div className="snap_category">
            <Filter
                category_list={categoryProps}
                selectedCategories={selectedCategories}
                setSelectedCategories={setSelectedCategories}
            />
        </div>
    );
};

export default SnapCategory;