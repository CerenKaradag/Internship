import React from "react";

const SearchBox = (props) => {
    return(
        <h4><input type="search"
        className= "search"
        placeholder={props.placeholder}
        onChange={props.handleChange}
        /></h4>
    )
}
export default SearchBox;